package com.cbyk.blogg.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogPosts")
public class BlogPost extends BlogBaseObject {

    public String title;
    public String subTitle;
    public String blogBody;

    public String formattedDate() {
        return creationDate.toString();
    }

    public BlogPost(String title, String subTitle, String blogBody, String authorFullname, String date) {
        this.title = title;
        this.subTitle = subTitle;
        this.blogBody = blogBody;
    }

    public BlogPost() {
    }

    public BlogPost(BlogRequest request) {
        this.blogBody = request.blogBody;
        this.title = request.title;
    }

    public BlogPost(String b, String t) {
        this.blogBody = t;
        this.title = b;
    }
}
