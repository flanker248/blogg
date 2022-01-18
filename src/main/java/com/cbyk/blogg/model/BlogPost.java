package com.cbyk.blogg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogPosts")
public class BlogPost {


    @Id
    public String id;

    public String title;
    public String subTitle;
    public String blogBody;
    public String authorFullname;
    public String date;

    public String formattedDate() {
        return date;
    }

    public BlogPost(String title, String subTitle, String blogBody, String authorFullname, String date) {
        this.title = title;
        this.subTitle = subTitle;
        this.blogBody = blogBody;
        this.authorFullname = authorFullname;
        this.date = date;
    }

    public BlogPost(){}

    public BlogPost(BlogRequest request) {
        this.blogBody = request.blogBody;
        this.title = request.title;
    }

}
