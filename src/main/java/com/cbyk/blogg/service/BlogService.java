package com.cbyk.blogg.service;


import com.cbyk.blogg.aop.Monitor;
import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.repo.BlogPostsRepository;
import com.cbyk.blogg.util.BlogStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Monitor
public class BlogService {

    @Autowired
    BlogPostsRepository repository;

    public Boolean saveBlog(BlogPost blog) {
        repository.save(blog);
        return true;
    }

    public boolean inactivateBlog(String uid) {
        BlogPost blogPost=repository.findById(uid).get();
        blogPost.status= BlogStatus.DELETED;
        repository.save(blogPost);
        return true;
    }

    public boolean deleteBlog(String uid) {
        repository.deleteById(uid);
        return true;
    }


    public List<BlogPost> fetchActiveBlogs() {
        return repository.findAllByStatus(BlogStatus.ACTIVE.toString());
    }

    public BlogPost getBlogById(String id) {
        return repository.findById(id).orElse(null);
    }
}
