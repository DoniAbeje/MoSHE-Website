package com.doniabeje.moshewebsite.domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @NotEmpty(message = "Title can not be Empty")
    private String title;

    @NotEmpty(message = "Description can not be Empty")
    @Column(length = 65535, columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dateTime = new Date();

    @NotNull(message = "Language can not be blank")
    private News.Language language = News.Language.ENGLISH;

    private String link;




}
