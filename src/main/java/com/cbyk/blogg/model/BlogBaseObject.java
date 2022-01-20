package com.cbyk.blogg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.*;

import java.util.Date;

public abstract class BlogBaseObject {

    @Id
    public  String id;

    @JsonIgnore
    public   boolean isActive = true;

    @CreatedBy
    public  String createdBy;

    @CreatedDate
    public  Date creationDate = new Date();

    @LastModifiedDate
    public  Date lastModifiedDate;

}
