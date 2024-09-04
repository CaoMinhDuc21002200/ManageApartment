package com.cmd.manageapartment.manageapartment.api.models;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.jdbc.Expectation;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "residents")
public class Residents {

    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "identity_card_number", nullable = false)
    private String identityCardNumber;

    @Column(name = "relationship_to_owner")
    private String relationshipToOwner;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    public Residents() {}

    public Residents(String fullName,
                     String sex,
                     LocalDateTime dateOfBirth,
                     String phone,
                     String email,
                     String identityCardNumber,
                     String relationshipToOwner
                     ) {

        this.fullName = fullName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.identityCardNumber = identityCardNumber;
        this.relationshipToOwner = relationshipToOwner;

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getRelationshipToOwner() {
        return relationshipToOwner;
    }

    public void setRelationshipToOwner(String relationshipToOwner) {
        this.relationshipToOwner = relationshipToOwner;
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

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Residents{" +
                "id=" + id +"\n"+
                ", fullName='" + fullName + '\'' +"\n"+
                ", sex='" + sex + '\'' +"\n"+
                ", dateOfBirth=" + dateOfBirth +"\n"+
                ", phone='" + phone + '\'' +"\n"+
                ", email='" + email + '\'' +"\n"+
                ", identityCardNumber='" + identityCardNumber + '\'' +"\n"+
                ", relationshipToOwner='" + relationshipToOwner + '\'' +"\n"+
                ", createdAt=" + createdAt +"\n"+
                ", updatedAt=" + updatedAt +"\n"+
                ", apartment=" + apartment +"\n"+
                '}';
    }
}


