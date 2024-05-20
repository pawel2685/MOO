package pl.wsb.Moo.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wsb.Moo.Model.OrbitClassEntity;
import pl.wsb.Moo.Repository.OrbitClassRepository;

import java.util.Iterator;

@Service
public class NeoWsSaveService {

    private static final Logger logger = LoggerFactory.getLogger(NeoWsSaveService.class);

    private final RestTemplate restTemplate;
    private final OrbitClassRepository orbitClassRepository;

    public NeoWsSaveService(RestTemplate restTemplate, OrbitClassRepository orbitClassRepository) {
        this.restTemplate = restTemplate;
        this.orbitClassRepository = orbitClassRepository;
    }

    public void saveNearEarthObjects() {
        String url = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=DEMO_KEY";
        String jsonResponse = restTemplate.getForObject(url, String.class);

        try {
            logger.info("Received response: " + jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);
            Iterator<JsonNode> elements = root.path("near_earth_objects").elements();

            while (elements.hasNext()) {
                JsonNode neoNode = elements.next();
                JsonNode orbitClassNode = neoNode.path("orbital_data").path("orbit_class");
                JsonNode estimatedDiameterNode = neoNode.path("estimated_diameter").path("kilometers");
                JsonNode closeApproachDataArray = neoNode.path("close_approach_data");

                if (!orbitClassNode.isMissingNode() && !estimatedDiameterNode.isMissingNode() && closeApproachDataArray.isArray() && closeApproachDataArray.size() > 0) {
                    JsonNode closeApproachDataNode = closeApproachDataArray.get(0);
                    JsonNode relativeVelocityNode = closeApproachDataNode.path("relative_velocity");
                    JsonNode missDistanceNode = closeApproachDataNode.path("miss_distance");

                    if (!relativeVelocityNode.isMissingNode() && !missDistanceNode.isMissingNode()) {
                        String orbitClassType = orbitClassNode.path("orbit_class_type").asText();
                        String orbitClassDescription = orbitClassNode.path("orbit_class_description").asText();
                        double estimatedDiameterMin = estimatedDiameterNode.path("estimated_diameter_min").asDouble();
                        double estimatedDiameterMax = estimatedDiameterNode.path("estimated_diameter_max").asDouble();
                        double relativeVelocityKmPerHour = relativeVelocityNode.path("kilometers_per_hour").asDouble();
                        double missDistanceKm = missDistanceNode.path("kilometers").asDouble();

                        OrbitClassEntity orbitClass = new OrbitClassEntity();
                        orbitClass.setOrbitClassType(orbitClassType);
                        orbitClass.setOrbitClassDescription(orbitClassDescription);
                        orbitClass.setEstimatedDiameterMin(estimatedDiameterMin);
                        orbitClass.setEstimatedDiameterMax(estimatedDiameterMax);
                        orbitClass.setRelativeVelocityKmPerHour(relativeVelocityKmPerHour);
                        orbitClass.setMissDistanceKm(missDistanceKm);

                        orbitClassRepository.save(orbitClass); // Save to database

                        logger.info("Saved OrbitClass: type=" + orbitClassType + ", description=" + orbitClassDescription + ", diameterMin=" + estimatedDiameterMin + ", diameterMax=" + estimatedDiameterMax + ", velocityKmPerHour=" + relativeVelocityKmPerHour + ", missDistanceKm=" + missDistanceKm);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error parsing response", e);
        }
    }
}

