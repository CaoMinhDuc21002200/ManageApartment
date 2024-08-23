//package com.cmd.manageapartment.manageapartment.api.controller;
//
//import com.cmd.manageapartment.manageapartment.api.models.Owners;
//import com.cmd.manageapartment.manageapartment.api.service.OwnersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/owners")
//public class OwnersController {
//
//    private final OwnersService ownersService;
//
//    @Autowired
//    public OwnersController(OwnersService ownersService) {
//        this.ownersService = ownersService;
//    }
//
//    //Post
//    @PostMapping
//    public ResponseEntity<Owners> createOwner(@RequestBody Owners owner) {
//        Owners createdOwner = ownersService.createOwner(owner);
//        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Owners> getOwnerById(@PathVariable UUID id) {
//        Optional<Owners> owner = ownersService.getOwnerById(id);
//        return owner.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Owners>> getAllOwners() {
//        List<Owners> owners = ownersService.getAllOwners();
//        return new ResponseEntity<>(owners, HttpStatus.OK);
//    }
//
//    //Put
//    @PutMapping("/{id}")
//    public ResponseEntity<Owners> updateOwnerById(@PathVariable UUID id,@RequestBody Owners owner) {
//            Owners updatedOwner = ownersService.updateOwner(owner, id);
//            return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Owners> deleteOwnerById(@PathVariable UUID id) {
//        ownersService.deleteOwnerById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}
