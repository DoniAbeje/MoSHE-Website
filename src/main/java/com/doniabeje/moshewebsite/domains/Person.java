package com.doniabeje.moshewebsite.domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @NotEmpty(message = "Full Name can not be Empty")
    private String name;

    @NotEmpty(message = "Description can not be Empty")
    @Column(length = 65535, columnDefinition = "text")
    private String description;

    @ManyToOne
    private Job job;

    @NotNull(message = "Language can not be blank")
    private News.Language language = News.Language.ENGLISH;

    @Temporal(TemporalType.DATE)
    private Date dateTime = new Date();

    private String image;




}
