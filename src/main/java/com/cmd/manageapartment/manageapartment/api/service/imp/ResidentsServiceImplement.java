package com.cmd.manageapartment.manageapartment.api.service.imp;


import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.Residents;
//import com.cmd.manageapartment.manageapartment.api.repository.ResidentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.ResidentsRepository;
//import com.cmd.manageapartment.manageapartment.api.service.ResidentService;
import com.cmd.manageapartment.manageapartment.api.service.ResidentsService;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ResidentsServiceImplement implements ResidentsService {
    private final ResidentsRepository residentsRepository;
    private final ApartmentRepository apartmentRepository;
    private final Logger logger = Logger.getLogger(ResidentsServiceImplement.class.getName());
    @Autowired
    public ResidentsServiceImplement(ResidentsRepository residentsRepository,
                                     ApartmentRepository apartmentRepository) {
        this.residentsRepository = residentsRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public List<Residents> getResidentsByName(String fullname) {
//        List<Residents> foundResidents = new ArrayList<>();
//        for(Residents resident : residentsRepository.findAll()) {
//            System.out.println(fullname);
//            if(resident.getFullName().equals(fullname)) {
//                foundResidents.add(resident);
//            }
//
//            logger.info("List resident:" + resident.getFullName());
//        }
//        return foundResidents;
        return residentsRepository.findByFullName(fullname);
    }


    @Override
    public Residents createResidentWithApartmentNumber(String apartmentNumber, Residents residents) {
        Apartment apartment = apartmentRepository.findByApartmentNumber(apartmentNumber).orElse(null);
        if(apartment != null) {
            residents.setApartment(apartment);
            return residentsRepository.save(residents);

        }else
            throw new IllegalArgumentException("Apartment not found");
    }

    @Override
    public Optional<Residents> getResidentById(UUID id){
        return residentsRepository.findById(id);
    }

    @Override
    public List<Residents> getAllResidents(){
        return residentsRepository.findAll();
    }

    @Override
    public void deleteResidentById(UUID id) {
        residentsRepository.deleteById(id);
    }

    @Override
    public Residents updateResidentById(UUID id, Residents residents) {
        Residents existingResidents = residentsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Resident not found."));

        existingResidents.setFullName(residents.getFullName());
        existingResidents.setSex(residents.getSex());
        existingResidents.setEmail(residents.getEmail());
        existingResidents.setDateOfBirth(residents.getDateOfBirth());
        existingResidents.setPhone(residents.getPhone());
        existingResidents.setRelationshipToOwner(residents.getRelationshipToOwner());
        return residentsRepository.save(existingResidents);
    }

    @Override
    public List<Residents> getResidentByApartmentId(UUID apartmentId){

        return residentsRepository.findByApartmentId(apartmentId);
    }


}
