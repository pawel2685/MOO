package pl.wsb.Moo.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wsb.Moo.Model.OrbitClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class NeoWsService {

    private static final Logger logger = LoggerFactory.getLogger(NeoWsService.class);

    private final RestTemplate restTemplate;

    public NeoWsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrbitClass> getNearEarthObjects() {
        String url = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=DEMO_KEY";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        List<OrbitClass> orbitClasses = new ArrayList<>();

        try {
            logger.info("Received response: " + jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);
            Iterator<JsonNode> elements = root.path("near_earth_objects").elements();

            while (elements.hasNext()) {
                JsonNode neoNode = elements.next();
                JsonNode orbitClassNode = neoNode.path("orbital_data").path("orbit_class");
                JsonNode estimatedDiameterNode = neoNode.path("estimated_diameter").path("kilometers");
                JsonNode closeApproachDataNode = neoNode.path("close_approach_data").get(0);
                JsonNode relativeVelocityNode = closeApproachDataNode.path("relative_velocity");
                JsonNode missDistanceNode = closeApproachDataNode.path("miss_distance");

                if (!orbitClassNode.isMissingNode() && !estimatedDiameterNode.isMissingNode() &&
                        !relativeVelocityNode.isMissingNode() && !missDistanceNode.isMissingNode()) {
                    String orbitClassType = orbitClassNode.path("orbit_class_type").asText();
                    String orbitClassDescription = orbitClassNode.path("orbit_class_description").asText();
                    double estimatedDiameterMin = estimatedDiameterNode.path("estimated_diameter_min").asDouble();
                    double estimatedDiameterMax = estimatedDiameterNode.path("estimated_diameter_max").asDouble();
                    double relativeVelocityKmPerHour = relativeVelocityNode.path("kilometers_per_hour").asDouble();
                    double missDistanceKm = missDistanceNode.path("kilometers").asDouble();

                    OrbitClass orbitClass = new OrbitClass(orbitClassType, orbitClassDescription, estimatedDiameterMin, estimatedDiameterMax, relativeVelocityKmPerHour, missDistanceKm);
                    orbitClasses.add(orbitClass);
                    logger.info("Parsed OrbitClass: type=" + orbitClassType + ", description=" + orbitClassDescription + ", diameterMin=" + estimatedDiameterMin + ", diameterMax=" + estimatedDiameterMax + ", velocityKmPerHour=" + relativeVelocityKmPerHour + ", missDistanceKm=" + missDistanceKm);
                }
            }
        } catch (Exception e) {
            logger.error("Error parsing response", e);
        }

        return orbitClasses;
    }

    public OrbitClass getFirstNearEarthObject() {
        List<OrbitClass> orbitClasses = getNearEarthObjects();
        return orbitClasses.isEmpty() ? null : orbitClasses.remove(0);
    }
}

