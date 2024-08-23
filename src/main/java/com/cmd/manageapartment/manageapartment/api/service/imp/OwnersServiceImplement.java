//package com.cmd.manageapartment.manageapartment.api.service.imp;
//
//import com.cmd.manageapartment.manageapartment.api.models.Owners;
//import com.cmd.manageapartment.manageapartment.api.repository.OwnersRepository;
//import com.cmd.manageapartment.manageapartment.api.service.OwnersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class OwnersServiceImplement implements OwnersService {
//
//    private final OwnersRepository ownersRepository;
//
//    @Autowired
//    public OwnersServiceImplement(OwnersRepository ownersRepository) {
//        this.ownersRepository = ownersRepository;
//    }
//
//    @Override
//    public Owners createOwner(Owners owner) {
//        return ownersRepository.save(owner);
//    }
//
//    @Override
//    public Optional<Owners> getOwnerById(UUID ownersID){
//        return ownersRepository.findById(ownersID);
//    }
//
//    //Update ID (add more later.....)
//    @Override
//    public Owners updateOwner(Owners updatedOwner, UUID ownersID) {
//        Optional<Owners> existingOwner = ownersRepository.findById(ownersID);
//            if (existingOwner.isPresent()){
//                Owners ownerToUpdated = existingOwner.get();
//
//                ownerToUpdated.setFullName(updatedOwner.getFullName());
//                ownerToUpdated.setEmail(updatedOwner.getEmail());
//                ownerToUpdated.setPhone(updatedOwner.getPhone());
//                ownerToUpdated.setDateOfBirth(updatedOwner.getDateOfBirth());
//                ownerToUpdated.setSex(updatedOwner.getSex());
//
//                return ownersRepository.save(ownerToUpdated);
//            }
//            else throw new RuntimeException("Owner with ID " + ownersID + " does not exist");
//        }
//
//
//    @Override
//    public List<Owners> getAllOwners(){
//        return ownersRepository.findAll();
//    }
//
//    @Override
//    public void deleteOwnerById(UUID ownersID) {
//        ownersRepository.deleteById(ownersID);
//    }
//
//}
