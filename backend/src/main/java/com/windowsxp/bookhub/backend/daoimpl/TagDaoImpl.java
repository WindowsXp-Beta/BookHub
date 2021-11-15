package com.windowsxp.bookhub.backend.daoimpl;

import com.windowsxp.bookhub.backend.dao.TagDao;
import com.windowsxp.bookhub.backend.entity.Tag;
import com.windowsxp.bookhub.backend.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TagDaoImpl implements TagDao {

    private final TagRepository tagRepository;
    @Override
    public List<Tag> findDoubleTagsByContent(String content) {
        return tagRepository.findDoubleTagsByContent(content);
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag findTagById(Long id) {
        return tagRepository.findTagById(id);
    }
}
