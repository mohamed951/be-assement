package com.assesment.fileuploadserviceapp.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Data
@Entity
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @JdbcTypeCode(Types.VARBINARY)
    private byte[] binaryData;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}