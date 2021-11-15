package com.windowsxp.bookhub.backend.dao;

import com.windowsxp.bookhub.backend.entity.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> findDoubleTagsByContent(String content);
    void saveTag(Tag tag);
    Tag findTagById(Long id);
}
