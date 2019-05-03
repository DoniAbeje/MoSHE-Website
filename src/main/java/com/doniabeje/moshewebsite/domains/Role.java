package com.doniabeje.moshewebsite.domains;


import javax.persistence.*;
import lombok.*;


@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
}
