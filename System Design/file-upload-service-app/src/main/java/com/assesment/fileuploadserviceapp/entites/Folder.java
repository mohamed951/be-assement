package com.assesment.fileuploadserviceapp.entites;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
@DiscriminatorValue("Folder")
public class Folder extends Item {

    @OneToMany(mappedBy = "parent")
    private List<File> files;
}