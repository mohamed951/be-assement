package com.assesment.fileuploadserviceapp.repositories;

import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

    Optional<PermissionGroup> findByName(String name);
}
