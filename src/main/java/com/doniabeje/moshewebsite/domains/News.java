package com.doniabeje.moshewebsite.domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class News {

    public enum Language {ENGLISH, AMHARIC}

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

    @NotEmpty(message = "Author can not be Empty")
    private String author = "Admin";

    @NotNull(message = "Language can not be blank")
    private Language language = Language.ENGLISH;

    @OneToMany(orphanRemoval = true)
    private List<NewsImage> images = new ArrayList<>();

    @Column(length = 65535, columnDefinition = "text")
    private String content;

    private boolean isVideo;

    private String videoLink;

    @ManyToOne
    private Document document;

}
