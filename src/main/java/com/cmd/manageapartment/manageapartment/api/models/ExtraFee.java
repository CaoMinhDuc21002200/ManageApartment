package com.cmd.manageapartment.manageapartment.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "extra_fee")
public class ExtraFee {


    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "general_hygiene_fee", nullable = false)
    private BigDecimal generalHygieneFee;

    @Column(name = "elevator_maintenance_fee", nullable = false)
    private BigDecimal elevatorMaintenanceFee;

    @Column(name = "parking_fee", nullable = false)
    private BigDecimal parkingFee;

    @Column(name = "total_extra_fee", nullable = false)
    private BigDecimal totalExtraFee;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ExtraFee() {}
    
    public ExtraFee(Apartment apartment) {

        this.apartment = apartment;
    }

    private void calculateFees() {
        Integer floorLevel = this.apartment.getFloorLevel();

        if (floorLevel == null) {
            floorLevel = 0; // or another default value
        }

        // Calculate elevator maintenance fee based on the floor level
        if (floorLevel <= 10) {
            this.elevatorMaintenanceFee = BigDecimal.valueOf(50000);
        } else if (floorLevel <= 15) {
            this.elevatorMaintenanceFee = BigDecimal.valueOf(75000);
        } else {
            this.elevatorMaintenanceFee = BigDecimal.valueOf(100000);
        }

        // Set fixed fees for parking and general hygiene
        this.parkingFee = BigDecimal.valueOf(50000);
        this.generalHygieneFee = BigDecimal.valueOf(100000);

        // Calculate the total extra fee
        this.totalExtraFee = this.elevatorMaintenanceFee
                .add(this.parkingFee)
                .add(this.generalHygieneFee);
    }

    // Call this method whenever fees need to be recalculated
    public void recalculateFees() {
        calculateFees();
    }


    public BigDecimal getGeneralHygieneFee() {
        return generalHygieneFee;
    }

    public void setGeneralHygieneFee(BigDecimal generalHygieneFee) {
        this.generalHygieneFee = generalHygieneFee;
    }

    public BigDecimal getElevatorMaintenanceFee() {
        return elevatorMaintenanceFee;
    }

    public void setElevatorMaintenanceFee(BigDecimal elevatorMaintenanceFee) {
        this.elevatorMaintenanceFee = elevatorMaintenanceFee;
    }

    public BigDecimal getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
    }

    public BigDecimal getTotalExtraFee() {
        return totalExtraFee;
    }

    public void setTotalExtraFee(BigDecimal totalExtraFee) {
        this.totalExtraFee = totalExtraFee;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExtraFee{" +
                "id=" + id +
                ", generalHygieneFee=" + generalHygieneFee +
                ", elevatorMaintenanceFee=" + elevatorMaintenanceFee +
                ", parkingFee=" + parkingFee +
                ", totalExtraFee=" + totalExtraFee +
                ", apartment=" + apartment +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
