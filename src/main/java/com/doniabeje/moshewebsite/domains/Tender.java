package com.doniabeje.moshewebsite.domains;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @NotEmpty(message = "Title can not be Empty")
    private String title;

    @NotEmpty(message = "Description can not be Empty")
    @Column(length = 65535, columnDefinition = "text")
    private String description;

    @Column(length = 65535, columnDefinition = "text")
    private String content;

    @Temporal(TemporalType.DATE)
    private Date deadline = new Date(Calendar.getInstance().getTimeInMillis());

    private String image;

    @Temporal(TemporalType.DATE)
    private Date dateTime = new Date();

    private News.Language language = News.Language.ENGLISH;

}
