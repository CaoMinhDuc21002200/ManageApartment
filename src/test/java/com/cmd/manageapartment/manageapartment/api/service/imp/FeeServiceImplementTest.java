package com.cmd.manageapartment.manageapartment.api.service.imp;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.ExtraFee;
import com.cmd.manageapartment.manageapartment.api.models.Fee;
import com.cmd.manageapartment.manageapartment.api.models.PaymentStatus;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.FeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class FeeServiceImplementTest {
    @Mock
    private FeeRepository feeRepository;

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private FeeServiceImplement feeService;

    private Fee fee;
    private Apartment apartment;
    private UUID apartmentId;
    private UUID feeId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);



        apartmentId = UUID.randomUUID();
        apartment = new Apartment("A101",
                1200.12F,
                5,
                11,
                true,
                true,
                LocalDateTime.now(),
                LocalDateTime.now());

        apartment.setId(apartmentId);

        feeId = UUID.randomUUID();
        fee = new Fee();
        fee.setId(feeId);
        fee.setElectricityUsage(100.0);
        fee.setWaterUsage(10.0);
        fee.setApartment(apartment);
        fee.setStatus(PaymentStatus.PENDING);
        fee.setUpdatedAt(LocalDateTime.now());
        fee.setCreatedAt(LocalDateTime.now());

        ExtraFee extraFee = new ExtraFee();
        extraFee.setId(UUID.randomUUID());
        extraFee.setApartment(apartment);
        extraFee.recalculateFees();
        fee.setTotal_extra_fee(extraFee.getTotalExtraFee());

    }

    @Test
    public void testCreateFeeForApartment() {
        // Mock repository behavior
        when(apartmentRepository.findByApartmentNumber(anyString())).thenReturn(Optional.of(apartment));
        when(feeRepository.save(any(Fee.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // Call the method to be tested
        feeService.createFeeForApartment("A101", fee);

        // Verify interactions and assert results
        verify(apartmentRepository).findByApartmentNumber("A101");
        verify(feeRepository).save(any(Fee.class));
        assertThat(fee.getTotal_extra_fee()).isNotNull();
        assertThat(fee.getApartment()).isEqualTo(apartment);
        assertThat(fee.getStatus()).isEqualTo(PaymentStatus.PENDING);
    }

    @Test
    public void testGetFeeById() {
        // Mock repository behavior
        when(feeRepository.findById(feeId)).thenReturn(Optional.of(fee));

        // Call the method to be tested
        Optional<Fee> retrievedFee = feeService.getFeeById(feeId);

        // Verify interactions and assert results
        verify(feeRepository).findById(feeId);
        assertThat(retrievedFee).isPresent();
        assertThat(retrievedFee.get()).isEqualTo(fee);
    }

    @Test
    public void testUpdateFeeById() {
        // Mock repository behavior
        when(feeRepository.findById(feeId)).thenReturn(Optional.of(fee));
        when(feeRepository.save(any(Fee.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // Update fee details
        Fee updatedFee = new Fee();
        ExtraFee extraFee = new ExtraFee();
        extraFee.setApartment(apartment);
        extraFee.recalculateFees();

        updatedFee.setId(UUID.randomUUID());
        updatedFee.setElectricityUsage(200.0);
        updatedFee.setWaterUsage(20.0);
        updatedFee.setStatus(PaymentStatus.COMPLETED);
        updatedFee.setApartment(apartment);
//        updatedFee.setUpdatedAt(LocalDateTime.now());
//        updatedFee.setCreatedAt(LocalDateTime.now());
//        updatedFee.setTotal_extra_fee(extraFee.getTotalExtraFee());
        System.out.println(updatedFee);

        // Call the method to be tested
        Fee result = feeService.updateFeeById(feeId, updatedFee);

        // Verify interactions and assert results
        verify(feeRepository).findById(feeId);
        verify(feeRepository).save(any(Fee.class));
        assertThat(result.getElectricityUsage()).isEqualTo(200.0);
        assertThat(result.getWaterUsage()).isEqualTo(20.0);
        assertThat(result.getStatus()).isEqualTo(PaymentStatus.COMPLETED);
    }

}