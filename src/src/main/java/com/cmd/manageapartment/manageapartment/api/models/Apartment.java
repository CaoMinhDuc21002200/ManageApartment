package com.cmd.manageapartment.manageapartment.api.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "apartment_number", nullable = false)
    private String apartmentNumber;

    @Column(name = "square_footage")
    private Float squareFootage;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "floor_level")
    private Integer floorLevel;

    @Column(name = "neighbor_safety")
    private Boolean neighborSafety;

    @Column(name = "repair_status")
    private Boolean repairStatus;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fee> fees= new ArrayList<>();

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Residents> residents= new ArrayList<>();

    public Apartment() {}

    public Apartment(String apartmentNumber,
                     Float squareFootage,
                     Integer rooms,
                     Integer floorLevel,
                     Boolean neighborSafety,
                     Boolean repairStatus,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {
        this.apartmentNumber = apartmentNumber;
        this.squareFootage = squareFootage;
        this.rooms = rooms;
        this.floorLevel = floorLevel;
        this.neighborSafety = neighborSafety;
        this.repairStatus = repairStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public float getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(float squareFootage) {
        this.squareFootage = squareFootage;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getFloorLevel() {
        return floorLevel;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setFloorLevel(Integer floorLevel) {
        this.floorLevel = floorLevel;
    }

    public boolean isNeighborSafety() {
        return neighborSafety;
    }

    public void setNeighborSafety(boolean neighborSafety) {
        this.neighborSafety = neighborSafety;
    }

    public boolean isRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(boolean repairStatus) {
        this.repairStatus = repairStatus;
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

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", squareFootage=" + squareFootage +
                ", rooms=" + rooms +
                ", floorLevel=" + floorLevel +
                ", neighborSafety=" + neighborSafety +
                ", repairStatus=" + repairStatus +
                ", isDelete=" + isDelete +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
