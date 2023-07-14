package com.assesment.fileuploadserviceapp.entites;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("File")
public class File extends Item {

    @Override
    public String getType() {
        return File.class.getSimpleName();
    }

    @Override
    public String getPath() {
        return getParent().getPath() + "/" + getName();
    }
}