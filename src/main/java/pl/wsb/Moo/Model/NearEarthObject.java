package pl.wsb.Moo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NearEarthObject {

    @JsonProperty("neo_reference_id")
    private String neoReferenceId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("absolute_magnitude_h")
    private double absoluteMagnitudeH;

    @JsonProperty("is_potentially_hazardous_asteroid")
    private boolean isPotentiallyHazardousAsteroid;

    @JsonProperty("orbital_data")
    private OrbitalData orbitalData;

    // Gettery i settery

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrbitalData {

        @JsonProperty("orbit_class")
        private OrbitClass orbitClass;

        // Gettery i settery

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class OrbitClass {

            @JsonProperty("orbit_class_type")
            private String orbitClassType;

            @JsonProperty("orbit_class_description")
            private String orbitClassDescription;

            // Gettery i settery

            public String getOrbitClassType() {
                return orbitClassType;
            }

            public void setOrbitClassType(String orbitClassType) {
                this.orbitClassType = orbitClassType;
            }

            public String getOrbitClassDescription() {
                return orbitClassDescription;
            }

            public void setOrbitClassDescription(String orbitClassDescription) {
                this.orbitClassDescription = orbitClassDescription;
            }
        }

        public OrbitClass getOrbitClass() {
            return orbitClass;
        }

        public void setOrbitClass(OrbitClass orbitClass) {
            this.orbitClass = orbitClass;
        }
    }

    public String getNeoReferenceId() {
        return neoReferenceId;
    }

    public void setNeoReferenceId(String neoReferenceId) {
        this.neoReferenceId = neoReferenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAbsoluteMagnitudeH() {
        return absoluteMagnitudeH;
    }

    public void setAbsoluteMagnitudeH(double absoluteMagnitudeH) {
        this.absoluteMagnitudeH = absoluteMagnitudeH;
    }

    public boolean isPotentiallyHazardousAsteroid() {
        return isPotentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(boolean potentiallyHazardousAsteroid) {
        isPotentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    public OrbitalData getOrbitalData() {
        return orbitalData;
    }

    public void setOrbitalData(OrbitalData orbitalData) {
        this.orbitalData = orbitalData;
    }
}


