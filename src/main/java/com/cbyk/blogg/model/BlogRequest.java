package com.cbyk.blogg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "blogPosts")
public class BlogRequest {


    @Id
    public String id;

    public String title;
    public String subTitle;
    public String blogBody;
    public String authorFullname;
    public String date;
    public List<Label> labels;


    public String formattedDate() {
        return date;
    }

    public BlogRequest(String title, String subTitle, String blogBody, String authorFullname, String date) {
        this.title = title;
        this.subTitle = subTitle;
        this.blogBody = blogBody;
        this.authorFullname = authorFullname;
        this.date = date;
    }

    public BlogRequest() {
    }


}
