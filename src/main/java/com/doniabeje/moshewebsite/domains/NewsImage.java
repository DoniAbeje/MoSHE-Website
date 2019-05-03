package com.doniabeje.moshewebsite.domains;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class NewsImage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

//    @NotEmpty(message = "Image description is required")
    private String alt;

    private boolean isMain = false;

    @ManyToOne
    private News news;

    private String name;
}
