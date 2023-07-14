package com.assesment.fileuploadserviceapp.entites;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
@DiscriminatorValue("Space")
public class Space extends Item {

    @OneToMany(mappedBy = "parent")
    private List<Folder> folders;

    @OneToMany(mappedBy = "parent")
    private List<File> files;

    @Override
    public String getType() {
        return Space.class.getSimpleName();
    }

    @Override
    public String getPath() {
        return "/" + getName();
    }
}