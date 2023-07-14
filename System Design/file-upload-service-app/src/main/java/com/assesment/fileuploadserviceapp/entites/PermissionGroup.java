package com.assesment.fileuploadserviceapp.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}