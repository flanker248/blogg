package com.cbyk.blogg.model;

import com.cbyk.blogg.util.EntityStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "labels")
public class Label {
    @Id
    public String id;
    public String name;
    public String css;
    public EntityStatus status;

    public Label(LabelDTO labelDTO) {
        this.name = labelDTO.name;
        this.css = labelDTO.css;
        this.status=EntityStatus.ACTIVE;
    }

    public Label(){}
}
