package com.cbyk.blogg.repo;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LabelRepository extends MongoRepository<Label, String> {

    List<Label> findAllByStatus(String status);

}
