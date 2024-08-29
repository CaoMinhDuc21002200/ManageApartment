package com.cmd.manageapartment.manageapartment.api.repository;


import com.cmd.manageapartment.manageapartment.api.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByName(String name);


}
