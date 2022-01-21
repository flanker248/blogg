package com.cbyk.blogg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    public String username;
    public String password;
    public List<String> roles;


}