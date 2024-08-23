//package com.cmd.manageapartment.manageapartment.api.models;
//
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "resident")
//public class Resident {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private UUID id;
//
//    @Column(name = "full_name", nullable = false)
//    private String fullName;
//
//    @Column(name = "relationship_to_owner")
//    private String relationshipToOwner;
//
//    @Column(name = "sex")
//    private char sex;
//
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "apartment_id", nullable = false)
//    private Apartment apartment;
//
//    @CreationTimestamp
//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//
//    public Resident() {
//    }
//
//    public Resident(String fullName,char sex ,String relationshipToOwner, Apartment apartment) {
//        this.fullName = fullName;
//        this.sex = sex;
//        this.relationshipToOwner = relationshipToOwner;
//        this.apartment = apartment;
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    public char getSex() {
//        return sex;
//    }
//
//    public void setSex(char sex) {
//        this.sex = sex;
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getRelationshipToOwner() {
//        return relationshipToOwner;
//    }
//
//    public void setRelationshipToOwner(String relationshipToOwner) {
//        this.relationshipToOwner = relationshipToOwner;
//    }
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Apartment getApartment() {
//        return apartment;
//    }
//
//    public void setApartment(Apartment apartment) {
//        this.apartment = apartment;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    @Override
//    public String toString() {
//        return "Resident{" +
//                "id=" + id +
//                ", fullName='" + fullName + '\'' +
//                ", sex=" + sex + '\'' +
//                ", relationshipToOwner='" + relationshipToOwner + '\'' +
//                ", apartment=" + apartment +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                '}';
//    }
//
//}
