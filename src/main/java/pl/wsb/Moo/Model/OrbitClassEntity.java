package pl.wsb.Moo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrbitClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orbitClassType;
    private String orbitClassDescription;
    private double estimatedDiameterMin;
    private double estimatedDiameterMax;
    private double relativeVelocityKmPerHour;
    private double missDistanceKm;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}

