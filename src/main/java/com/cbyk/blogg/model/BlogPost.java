package com.cbyk.blogg.model;

import com.cbyk.blogg.util.AppConstants;
import com.cbyk.blogg.util.EntityStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Document(collection = "blogPosts")
public class BlogPost extends BlogBaseObject {

    public String title;
    public String subTitle;
    public String blogBody;
    public EntityStatus status;
    public List<String> labels;

    public String formattedDate() {
        return new SimpleDateFormat(AppConstants.DATE_FORMAT_DDMMYYYY).format(creationDate);
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
        this.labels = request.labels;
    }

    public BlogPost(String b, String t, EntityStatus status) {
        this.blogBody = t;
        this.title = b;
        this.status = status;
    }

    public BlogPost(Map<String, Object> request, EntityStatus status) {
        this.blogBody = (String) request.get("blogBody");
        this.title = (String) request.get("title");
        this.status = status;
    }
}
