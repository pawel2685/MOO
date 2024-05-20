package pl.wsb.Moo.Model;
import java.util.Objects;

public class OrbitClass {
    private String orbitClassType;
    private String orbitClassDescription;
    private double estimatedDiameterMin;
    private double estimatedDiameterMax;
    private double relativeVelocityKmPerHour;
    private double missDistanceKm;

    public OrbitClass(String orbitClassType, String orbitClassDescription, double estimatedDiameterMin, double estimatedDiameterMax, double relativeVelocityKmPerHour, double missDistanceKm) {
        this.orbitClassType = orbitClassType;
        this.orbitClassDescription = orbitClassDescription;
        this.estimatedDiameterMin = estimatedDiameterMin;
        this.estimatedDiameterMax = estimatedDiameterMax;
        this.relativeVelocityKmPerHour = relativeVelocityKmPerHour;
        this.missDistanceKm = missDistanceKm;
    }

    // Getters and setters

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

    public double getEstimatedDiameterMin() {
        return estimatedDiameterMin;
    }

    public void setEstimatedDiameterMin(double estimatedDiameterMin) {
        this.estimatedDiameterMin = estimatedDiameterMin;
    }

    public double getEstimatedDiameterMax() {
        return estimatedDiameterMax;
    }

    public void setEstimatedDiameterMax(double estimatedDiameterMax) {
        this.estimatedDiameterMax = estimatedDiameterMax;
    }

    public double getRelativeVelocityKmPerHour() {
        return relativeVelocityKmPerHour;
    }

    public void setRelativeVelocityKmPerHour(double relativeVelocityKmPerHour) {
        this.relativeVelocityKmPerHour = relativeVelocityKmPerHour;
    }

    public double getMissDistanceKm() {
        return missDistanceKm;
    }

    public void setMissDistanceKm(double missDistanceKm) {
        this.missDistanceKm = missDistanceKm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrbitClass that = (OrbitClass) o;
        return Double.compare(that.estimatedDiameterMin, estimatedDiameterMin) == 0 &&
                Double.compare(that.estimatedDiameterMax, estimatedDiameterMax) == 0 &&
                Double.compare(that.relativeVelocityKmPerHour, relativeVelocityKmPerHour) == 0 &&
                Double.compare(that.missDistanceKm, missDistanceKm) == 0 &&
                Objects.equals(orbitClassType, that.orbitClassType) &&
                Objects.equals(orbitClassDescription, that.orbitClassDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orbitClassType, orbitClassDescription, estimatedDiameterMin, estimatedDiameterMax, relativeVelocityKmPerHour, missDistanceKm);
    }
}
