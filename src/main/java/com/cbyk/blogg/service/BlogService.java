package com.cbyk.blogg.service;


import com.cbyk.blogg.aop.Monitor;
import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.repo.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Monitor
public class BlogService {

    @Autowired
    BlogPostsRepository repository;

    public boolean saveBlog(BlogPost blog) {
        System.out.println("Blog created");
        repository.save(blog);
        return true;
    }

    public boolean removeBlog(String uid) {
        repository.deleteById(uid);
        return true;
    }


    public List<BlogPost> fetchAllBlogs() {
        return repository.findAll();
    }

    public BlogPost getBlogById(String id) {
        return repository.findById(id).orElse(null);
    }
}
