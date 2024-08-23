//package com.cmd.manageapartment.manageapartment.api.repository;
//
//import com.cmd.manageapartment.manageapartment.api.models.Owners;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface OwnersRepository extends JpaRepository<Owners, UUID> {
//
//    List<Owners> findByFullName(String fullName);
//
//    Optional<Owners> findByEmail(String email);
//
//    boolean existsByEmail(String email);
//}
