package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

// It is used to mark the class as a persistent Java class.
@Entity
//used to provide the details of the table that this entity will be mapped to
@Table(name = "notes")
//Needed to createdAt and updatedAt fields automatically get populated whenever we create or update an entity + @EnableJpaAuditing in main class
@EntityListeners(AuditingEntityListener.class)
//is a Jackson annotation. Spring Boot uses Jackson for Serializing and Deserializing Java objects to and from JSONis a Jackson annotation. Spring Boot uses Jackson for Serializing and Deserializing Java objects to and from JSON
//This annotation is used because we don’t want the clients of the rest api to supply the createdAt and updatedAt values. If they supply these values then we’ll simply ignore them. However, we’ll include these values in the JSON response.
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Note implements Serializable {

    // used to define the primary key
    @Id
    //used to define the primary key generation strategy, we have declared the primary key to be an Auto Increment field
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //used to validate that the annotated field is not null or empty
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    //used to define the properties of the column that will be mapped to the annotated field. You can define several properties like name, length, nullable, updateable etc
    // By default, a field named createdAt is mapped to a column named created_at in the database table. i.e. all camel cases are replaced with underscores.
    // If you want to map the field to a different column, you can specify it using - @Column(name = "created_on")
    @Column(nullable = false, updatable = false)
    //used with java.util.Date and java.util.Calendar classes. It converts the date and time values from Java Object to compatible database type and vice versa
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
