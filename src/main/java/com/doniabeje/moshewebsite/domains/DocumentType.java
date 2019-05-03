package com.doniabeje.moshewebsite.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


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

}
