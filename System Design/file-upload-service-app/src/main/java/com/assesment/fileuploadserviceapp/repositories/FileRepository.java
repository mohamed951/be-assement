package com.assesment.fileuploadserviceapp.repositories;

import com.assesment.fileuploadserviceapp.entites.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
