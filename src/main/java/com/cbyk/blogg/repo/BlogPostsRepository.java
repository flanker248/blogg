package com.cbyk.blogg.repo;

import com.cbyk.blogg.model.BlogPost;
import com.mongodb.annotations.Beta;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BlogPostsRepository extends MongoRepository<BlogPost,String> {

    List<BlogPost> findByTitleContaining(String title);

}
