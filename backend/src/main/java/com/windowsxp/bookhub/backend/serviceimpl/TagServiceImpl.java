package com.windowsxp.bookhub.backend.serviceimpl;

import com.windowsxp.bookhub.backend.dao.TagDao;
import com.windowsxp.bookhub.backend.dto.request.TagDTO;
import com.windowsxp.bookhub.backend.entity.Tag;
import com.windowsxp.bookhub.backend.service.TagService;
import com.windowsxp.bookhub.backend.utils.LogUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    @Override
    public void addNewTag(TagDTO tagDTO) {
        Tag newTag = new Tag();
        newTag.setBookIdList(tagDTO.getBookIdList());
        newTag.setContent(tagDTO.getContent());
        LogUtil.debug(tagDTO.getRelationTagList());
        if(tagDTO.getRelationTagList() != null) {
            List<Long> list = tagDTO.getRelationTagList();
            for(Long tagId : list){
                newTag.addRelation(tagDao.findTagById(tagId));
            }
        }
        tagDao.saveTag(newTag);
    }
}
