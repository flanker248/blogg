package com.cbyk.blogg.service;


import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.repo.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogPostsRepository repository;

    public boolean saveBLog(BlogPost blog) {
        repository.save(blog);
        return true;
    }

    public List<BlogPost> fetchAllBlogs() {
        return repository.findAll();
    }


}
