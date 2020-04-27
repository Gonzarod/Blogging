package com.acme.blogging.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="posts")
@Getter
@Setter
public class Post extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true,nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 100,message = "Oe ctm menos de 100 caracteres")
    private String tittle;

    @NotNull
    @Size(max = 100,message = "hey menos de 100 caracter")
    private String description;

    @NotNull
    @Lob
    private String content;
}
