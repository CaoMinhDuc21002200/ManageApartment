//package com.cmd.manageapartment.manageapartment.api.models;
//
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Table(name = "owners")
//public class Owners {
//
//    @Id
//    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;
//
//    @Column(name = "full_name", length = 100, nullable = false)
//    private String fullName;
//
//    @Column(name = "email",nullable = false,length=100,unique = true)
//    private String email;
//
//    @Column(name = "phone",nullable = false, length = 15)
//    private String phone;
//
//    @Column(name = "date_of_birth")
//    private LocalDateTime dateOfBirth;
//
//    @Column(name ="sex")
//    private Character sex;
//
//    @ManyToOne
//    @JoinColumn(name="apartment_id",nullable = false)
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
//    public Owners(){}
//
//    public Owners(String fullName,
//                  String email,
//                  String phone,
//                  LocalDateTime dateOfBirth,
//                  char sex,
//                  Apartment apartment) {
//        this.fullName = fullName;
//        this.email = email;
//        this.phone = phone;
//        this.dateOfBirth = dateOfBirth;
//        this.sex = sex;
//        this.apartment = apartment;
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
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
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public LocalDateTime getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDateTime dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public Character getSex() {
//        return sex;
//    }
//
//    public void setSex(Character sex) {
//        this.sex = sex;
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
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    @Override
//    public String toString() {
//        return "Owner{" +
//                "id=" + id +
//                ", fullName='" + fullName + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", dateOfBirth=" + dateOfBirth +
//                ", sex=" + sex +
//                ", apartment=" + apartment +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                '}';
//    }
//
//
//}
