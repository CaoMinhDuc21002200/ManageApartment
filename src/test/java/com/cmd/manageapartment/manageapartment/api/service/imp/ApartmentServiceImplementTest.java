package com.cmd.manageapartment.manageapartment.api.service.imp;

import com.cmd.manageapartment.manageapartment.api.exception.ResourceNotFoundException;
import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.service.ApartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ApartmentServiceImplementTest {

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentServiceImplement apartmentService;

    private Apartment apartment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apartment = new Apartment();
        apartment.setId(UUID.randomUUID());
        apartment.setApartmentNumber("101");
        apartment.setSquareFootage(100.0F);
        apartment.setRooms(3);
        apartment.setFloorLevel(10);
        apartment.setNeighborSafety(true);
        apartment.setRepairStatus(false);
        apartment.setDelete(false);
        apartment.setAvailable(true);

    }

    @Test
    void createApartment() {

        when(apartmentRepository.save(any(Apartment.class))).thenReturn(apartment);

        Apartment result = apartmentService.createApartment(apartment);

        assertNotNull(result, "Result should not be null");
        assertEquals(apartment.getApartmentNumber(), result.getApartmentNumber());
        verify(apartmentRepository, times(1)).save(apartment);
    }

    @Test
    void getApartmentById() {
        when(apartmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(apartment));

        Optional<Apartment> result = apartmentService.getApartmentById(apartment.getId());

        assertTrue(result.isPresent());
        assertEquals(apartment.getApartmentNumber(), result.get().getApartmentNumber());
    }

    @Test
    void getApartmentByNumber() {
        when(apartmentRepository.findByApartmentNumber(anyString())).thenReturn(Optional.of(apartment));

        Optional<Apartment> result = apartmentService.getApartmentByNumber(apartment.getApartmentNumber());

        assertTrue(result.isPresent());
        assertEquals(apartment.getApartmentNumber(), result.get().getApartmentNumber());
    }

    @Test
    void getAllApartments() {
        List<Apartment> apartments = List.of(apartment);
        when(apartmentRepository.findAll()).thenReturn(apartments);

        List<Apartment> result = apartmentService.getAllApartments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(apartment.getApartmentNumber(), result.get(0).getApartmentNumber());
    }

    @Test
    void deleteApartmentByApartmentNumber() {
        when(apartmentRepository.findByApartmentNumber(anyString())).thenReturn(Optional.of(apartment));
        doNothing().when(apartmentRepository).delete(any(Apartment.class));

        apartmentService.deleteApartmentByApartmentNumber(apartment.getApartmentNumber());

        verify(apartmentRepository, times(1)).delete(apartment);
    }

    @Test
    void deleteLogicApartmentByNumber() {
        when(apartmentRepository.findByApartmentNumber(anyString())).thenReturn(Optional.of(apartment));
        when(apartmentRepository.save(any(Apartment.class))).thenReturn(apartment);

        apartmentService.deleteLogicApartmentByNumber(apartment.getApartmentNumber());

        verify(apartmentRepository, times(1)).save(apartment);
        assertTrue(apartment.getDelete());
    }

    @Test
    void updateApartment() {
        when(apartmentRepository.findByApartmentNumber(anyString())).thenReturn(Optional.of(apartment));
        when(apartmentRepository.save(any(Apartment.class))).thenReturn(apartment);

        Apartment updatedApartment = new Apartment();
        updatedApartment.setApartmentNumber("101");
        updatedApartment.setSquareFootage(1500.0F);
        updatedApartment.setRooms(4);
        updatedApartment.setFloorLevel(12);
        updatedApartment.setNeighborSafety(false);
        updatedApartment.setRepairStatus(true);
        updatedApartment.setDelete(false);
        updatedApartment.setAvailable(true);

        Apartment result = apartmentService.updateApartment(apartment.getApartmentNumber(), updatedApartment);

        assertNotNull(result);
        assertEquals(updatedApartment.getSquareFootage(), result.getSquareFootage());
        assertEquals(updatedApartment.getRooms(), result.getRooms());
        assertEquals(updatedApartment.getFloorLevel(), result.getFloorLevel());
        verify(apartmentRepository, times(1)).save(result);
    }
}