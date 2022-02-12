package com.cbyk.blogg.service;

import com.cbyk.blogg.model.Label;
import com.cbyk.blogg.model.LabelDTO;
import com.cbyk.blogg.repo.LabelRepository;
import com.cbyk.blogg.util.EntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    CacheService cacheService;

    @Cacheable("allActiveLabels")
    public List<Label> getAllActiveLabels() {
        return labelRepository.findAllByStatus(EntityStatus.ACTIVE.toString());
    }


    public boolean createLabel(LabelDTO labelDTO){
        labelRepository.save(new Label(labelDTO));
        cacheService.evictAllCacheValues("allActiveLabels");
        return true;
    }

    public boolean updateLabel(LabelDTO labelDTO){

        Query query= new Query();
        query.addCriteria(Criteria.where("name").is(labelDTO.name));

        Update update = new Update();
        update.set("status", labelDTO.status.toString());
        update.set("css", labelDTO.css);
        if(labelDTO.updatedName!=null && !"".equals(labelDTO.updatedName)){
            update.set("name", labelDTO.updatedName);
        }

        mongoTemplate.findAndModify(query, update, Label.class);
        cacheService.evictAllCacheValues("allActiveLabels");
        return true;
    }

}
