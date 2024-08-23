package com.cmd.manageapartment.manageapartment.api.models;

import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "fee")

public class Fee {


    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "electricity_usage")
    private Double electricityUsage = 0.0;

    @Column(name = "water_usage")
    private Double waterUsage = 0.0;

    @Column(name = "total_amount_due", nullable = false)
    private BigDecimal total_amount_due;

    @Column(name = "total_extra_fee", nullable = false)
    private BigDecimal total_extra_fee;

    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;


    public Fee(){}

    public Fee(Double electricityUsage, Double waterUsage, PaymentStatus status) {
        this.id = UUID.randomUUID();
        this.electricityUsage = electricityUsage != null ? electricityUsage : 0.0;
        this.waterUsage = waterUsage != null ? waterUsage : 0.0;
        this.status = status;
        calculateTotalAmountDue();
    }

    public BigDecimal getTotal_extra_fee() {
        return total_extra_fee;
    }

    public void setTotal_extra_fee(BigDecimal total_extra_fee) {
        this.total_extra_fee = total_extra_fee;
    }

    public void setElectricityUsage(Double electricityUsage) {

        this.electricityUsage = (electricityUsage != null && electricityUsage > 0) ? electricityUsage : 0.0;
        calculateTotalAmountDue();
    }

    public void setWaterUsage(Double waterUsage) {

        this.waterUsage = (waterUsage != null && waterUsage > 0) ? waterUsage : 0.0;;
        calculateTotalAmountDue();
    }

    public void setTotal_amount_due(BigDecimal total_amount_due) {
        this.total_amount_due = total_amount_due;
    }


    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return total_amount_due;
    }

    public void setAmount(BigDecimal total_amount_due) {
        this.total_amount_due = total_amount_due;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public UUID getApartmentId() {
        return apartment.getId();  // Accessing apartmentId through the Apartment entity
    }


    @Override
    public String toString() {

        return "Fee{" +
                "id=" + id +
                ", electricityUsage=" + electricityUsage +
                ", waterUsage=" + waterUsage +
                ", amount=" + total_amount_due +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", paymentDate=" + paymentDate +
                ", apartment_id=" + getApartmentId() +
                '}';
    }

    private double calculateElectricityCost(double usage) {
        double cost = 0.0;
        if (usage > 400) {
            cost += (usage - 400) * 3151;
            usage = 400;
        }
        if (usage > 300) {
            cost += (usage - 300) * 3050;
            usage = 300;
        }
        if (usage > 200) {
            cost += (usage - 200) * 2729;
            usage = 200;
        }
        if (usage > 100) {
            cost += (usage - 100) * 2167;
            usage = 100;
        }
        if (usage > 50) {
            cost += (usage - 50) * 1866;
            usage = 50;
        }
        cost += usage * 1806;
        return cost;
    }

    private void calculateTotalAmountDue() {
        double electricityCost = calculateElectricityCost(this.electricityUsage != null ? this.electricityUsage : 0.0);
        double waterCost = (this.waterUsage != null ? this.waterUsage : 0.0) * 3000;
        double totalCost = (electricityCost + waterCost) * 1.08; // Apply VAT
        this.total_amount_due = BigDecimal.valueOf(totalCost).setScale(2, RoundingMode.HALF_UP);
    }

}
