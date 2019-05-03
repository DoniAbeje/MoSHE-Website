package com.doniabeje.moshewebsite.domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class GeneralInforamtion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(length = 65535, columnDefinition = "text")
    private String amharicDescription;
    @Column(length = 65535, columnDefinition = "text")
    private String englishDescription;




    private String location;




    private String officeImage;






}
