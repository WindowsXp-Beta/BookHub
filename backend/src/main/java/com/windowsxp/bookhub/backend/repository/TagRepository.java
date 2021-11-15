package com.windowsxp.bookhub.backend.repository;

import com.windowsxp.bookhub.backend.entity.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface TagRepository extends Neo4jRepository<Tag, Long> {
    @Query("MATCH (tag:Tag)-[:related*0..2]->(c) where tag.content=~ ($content + '.*') return distinct c")
    List<Tag> findDoubleTagsByContent(String content);

    Tag findTagById(Long id);
}
