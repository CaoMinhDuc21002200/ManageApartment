//package com.cmd.manageapartment.manageapartment.api.service.imp;
//
//import com.cmd.manageapartment.manageapartment.api.models.Resident;
//import com.cmd.manageapartment.manageapartment.api.models.Residents;
//import com.cmd.manageapartment.manageapartment.api.repository.ResidentRepository;
//import com.cmd.manageapartment.manageapartment.api.service.ResidentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class ResidentServiceImplement implements ResidentService {
//        private final ResidentRepository residentRepository;
//
//        @Autowired
//        public ResidentServiceImplement(ResidentRepository residentRepository) {
//            this.residentRepository = residentRepository;
//        }
//
//        @Override
//        public Resident createResident(Resident resident) {
//            return residentRepository.save(resident);
//        }
//
//        @Override
//        public Optional<Resident> getResidentById(UUID id){
//            return residentRepository.findById(id);
//        }
//
//        @Override
//        public List<Resident> getAllResidents(){
//            return residentRepository.findAll();
//        }
//
//        @Override
//        public void deleteResidentById(UUID id) {
//            residentRepository.deleteById(id);
//        }
//
//        @Override
//        public Residents updateResident(UUID id, Residents residents) {
//            Residents existingResidents  = residentRepository.findById(id)
//                    .orElseThrow(()->new IllegalArgumentException("Resident not found."));
//            existingResidents.setFullName(residents.getFullName());
//            existingResidents.setRelationshipToOwner(residents.getRelationshipToOwner());
//            existingResidents.setApartment(residents.getApartment());
//            existingResidents.setSex(residents.getSex());
//            return residentRepository.save(existingResidents    );
//        }
//
//        @Override
//        public List<Residents> getResidentsByApartmentId(UUID apartmentId){
//
//            return residentRepository.findByApartmentId(apartmentId);
//        }
//
//
//}
