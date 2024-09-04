package com.cmd.manageapartment.manageapartment.api.service.imp;

import com.cmd.manageapartment.manageapartment.api.exception.ResourceNotFoundException;
import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@Service
public class ApartmentServiceImplement implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    //Debug
    private final static Logger logger = LoggerFactory.getLogger(ApartmentServiceImplement.class);

    @Autowired
    public ApartmentServiceImplement(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }
    @Override
    public Apartment createApartment(Apartment apartment) {
        try {
            apartment.setDelete(false);
            apartment.setAvailable(true);
            return apartmentRepository.save(apartment);
        }catch(DataIntegrityViolationException e) {
            logger.error("Apartment with number {} already exists.", apartment.getApartmentNumber());
            throw new DataIntegrityViolationException("Apartment already exists or failed to create.");
        }
    }

    @Override
    public Optional<Apartment> getApartmentById(UUID apartmentId) {

        Apartment apartment= apartmentRepository.findById(apartmentId).orElseThrow(()
                -> new ResourceNotFoundException("Apartment not found"));
        return Optional.of(apartment);
    }

    @Override
    public Optional<Apartment> getApartmentByNumber(String apartmentNumber){
        Apartment apartment = apartmentRepository.findByApartmentNumber(apartmentNumber).orElse(null);
        if (apartment == null) {
            throw new ResourceNotFoundException("Apartment not found");
        }
        return Optional.of(apartment);
    }

    @Override
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public void deleteApartmentByApartmentNumber(String apartmentNumber) {
        Apartment apartment = apartmentRepository.findByApartmentNumber(apartmentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment not found with number: " + apartmentNumber));
        apartmentRepository.delete(apartment);
    }

    @Override
    public void deleteLogicApartmentByNumber(String apartmentNumber){
        Apartment apartment = apartmentRepository.findByApartmentNumber(apartmentNumber).orElseThrow(null);
        if(apartment == null){
            throw new ResourceNotFoundException("Apartment not found");
        }
        apartment.setDelete(true);
        apartmentRepository.save(apartment);
    }

    @Override
    public Apartment updateApartment(String apartmentNumber, Apartment apartment){
        Optional<Apartment> existingApartment = apartmentRepository.findByApartmentNumber(apartmentNumber);
        if (existingApartment.isPresent()) {
            Apartment apartmentToUpdate = existingApartment.get();

            // Update fields with the new values
            apartmentToUpdate.setApartmentNumber(apartment.getApartmentNumber());
            apartmentToUpdate.setSquareFootage(apartment.getSquareFootage());
            apartmentToUpdate.setRooms(apartment.getRooms());
            apartmentToUpdate.setFloorLevel(apartment.getFloorLevel());
            apartmentToUpdate.setNeighborSafety(apartment.isNeighborSafety());
            apartmentToUpdate.setRepairStatus(apartment.isRepairStatus());
            apartmentToUpdate.setDelete(apartment.getDelete());
            apartmentToUpdate.setAvailable(apartment.getAvailable());

            // Save the updated apartment
            return apartmentRepository.save(apartmentToUpdate);
        } else {
            throw new RuntimeException("Apartment not found with number: " + apartmentNumber);
        }
    }

}
