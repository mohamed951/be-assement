package com.assesment.fileuploadserviceapp.entites;

import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup permissionGroup;
}