package com.cmd.manageapartment.manageapartment.api.service.imp;

import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.Residents;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.ResidentsRepository;
import com.cmd.manageapartment.manageapartment.api.service.ResidentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ResidentsServiceImplementTest {

    @Mock
    private ResidentsRepository residentsRepository;

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ResidentsServiceImplement residentsService;

    private Residents resident;
    private Apartment apartment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the Resident and Apartment objects for testing
        apartment = new Apartment();
        apartment.setId(UUID.randomUUID());
        apartment.setApartmentNumber("A101");
        apartment.setSquareFootage(45F);
        apartment.setFloorLevel(5);
        apartment.setRooms(4);
        apartment.setNeighborSafety(true);
        apartment.setRepairStatus(false);
        apartment.setAvailable(true);


        resident = new Residents();
        resident.setId(UUID.randomUUID());
        resident.setFullName("John Doe");
        resident.setDateOfBirth(LocalDateTime.of(1999,3,4,1,3,4));
        resident.setSex("Male");
        resident.setEmail("johndoe1@example.com");
        resident.setRelationshipToOwner("owner");

    }

    @Test
    void getResidentsByName() {
        // Arrange
        String fullName = "John Doe";
        when(residentsRepository.findByFullName(fullName)).thenReturn(List.of(resident));

        // Act
        List<Residents> foundResidents = residentsService.getResidentsByName(fullName);

        // Assert
        assertNotNull(foundResidents);
        assertEquals(1, foundResidents.size());
        assertEquals(resident.getFullName(), foundResidents.get(0).getFullName());
        verify(residentsRepository, times(1)).findByFullName(fullName);

    }

    @Test
    void createResidentWithApartmentNumber() {
        // Arrange
        when(apartmentRepository.findByApartmentNumber("A101")).thenReturn(Optional.of(apartment));
        when(residentsRepository.save(resident)).thenReturn(resident);

        // Act
        Residents createdResident = residentsService.createResidentWithApartmentNumber("A101", resident);

        // Assert
        assertNotNull(createdResident);
        assertEquals(apartment, createdResident.getApartment());
        verify(apartmentRepository, times(1)).save(apartment);
        verify(residentsRepository, times(1)).save(resident);
    }

    @Test
    void getResidentById() {
        UUID residentId = UUID.randomUUID();
        when(residentsRepository.findById(residentId)).thenReturn(Optional.of(resident));

        // Act
        Optional<Residents> foundResident = residentsService.getResidentById(residentId);

        // Assert
        assertTrue(foundResident.isPresent());
        assertEquals(resident.getFullName(), foundResident.get().getFullName());
        verify(residentsRepository, times(1)).findById(residentId);

    }

    @Test
    void deleteResidentById() {
        // Arrange
        UUID residentId = UUID.randomUUID();

        // Act
        residentsService.deleteResidentById(residentId);

        // Assert
        verify(residentsRepository, times(1)).deleteById(residentId);
    }

    @Test
    void updateResidentById() {
        // Arrange
        UUID residentId = resident.getId();
        Residents updatedResident = new Residents();
        updatedResident.setFullName("Jane Doe");
        updatedResident.setEmail("janedoe@example.com");

        when(residentsRepository.findById(residentId)).thenReturn(Optional.of(resident));
        when(residentsRepository.save(resident)).thenReturn(resident);

        // Act
        Residents result = residentsService.updateResidentById(residentId, updatedResident);

        // Assert
        assertEquals("Jane Doe", result.getFullName());
        assertEquals("janedoe@example.com", result.getEmail());
        verify(residentsRepository, times(1)).findById(residentId);
        verify(residentsRepository, times(1)).save(resident);
    }


}