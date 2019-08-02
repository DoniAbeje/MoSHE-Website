package com.doniabeje.moshewebsite.domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Entity
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @NotNull(message = "Language can not be blank")
    private News.Language language = News.Language.ENGLISH;

    @NotEmpty(message = "Document Title can not be Empty")
    private String title;

    @Column(length = 65535, columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dateTime = new Date();
}
