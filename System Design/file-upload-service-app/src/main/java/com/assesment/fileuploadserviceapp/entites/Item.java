package com.assesment.fileuploadserviceapp.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Item parent;

    public abstract String getType();

    public abstract String getPath();
}