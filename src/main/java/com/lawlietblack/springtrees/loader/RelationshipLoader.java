package com.lawlietblack.springtrees.loader;

import com.lawlietblack.springtrees.model.Relationship;
import com.lawlietblack.springtrees.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RelationshipLoader implements ApplicationListener<ContextRefreshedEvent>{
    private RelationshipRepository relationshipRepository;

    @Autowired
    public void setRelationshipRepository(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Relationship rel1 = new Relationship("partnered");
        Relationship rel2 = new Relationship("offspring");
        Relationship rel3 = new Relationship("siblings");
        relationshipRepository.save(rel1);
        relationshipRepository.save(rel2);
        relationshipRepository.save(rel3);
    }
}
