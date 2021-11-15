package com.windowsxp.bookhub.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
@NoArgsConstructor
@Setter
@Getter
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    private List<Integer> bookIdList;
    private String content;

    @Relationship(type = "related")
    Set<Tag> relatedTags;

    public void addRelation(Tag tag){
        if(relatedTags == null){
            relatedTags = new HashSet<>();
        }
        relatedTags.add(tag);
    }
}
