package com.assesment.fileuploadserviceapp.repositories;

import com.assesment.fileuploadserviceapp.entites.Permission;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    boolean existsByPermissionGroup_IdAndUserEmailAndPermissionLevelIn(Long groupId, String userEmail, Collection<PermissionLevel> permissionLevel);
}
