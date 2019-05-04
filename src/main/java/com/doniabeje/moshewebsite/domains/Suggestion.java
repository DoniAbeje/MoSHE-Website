package com.doniabeje.moshewebsite.domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @NotEmpty(message = "Name can not be empty")
    @Column(length = 65535, columnDefinition = "text")
    private String content;

    private String email;

    private String phone;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dateTime = new Date();

}
