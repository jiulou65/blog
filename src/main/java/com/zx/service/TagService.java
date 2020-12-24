package com.zx.service;

import com.zx.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    /**
     * 保存分类
     * @param tag
     * @return
     */
    public Tag saveTag(Tag tag);

    /**
     * 查询分类
     * @param id
     * @return
     */
    public Tag getTag(Long id);

    public Tag getTagByName(String name);

    /**
     * 展示分类
     * @param pageable
     * @return
     */
    public Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    /**
     * 更新分类
     * @param id
     * @param tag
     * @return
     */
    public Tag updateTag(Long id, Tag tag);

    /**
     * 删除分类
     * @param id
     * @return
     */
    public void deleteTag(Long id);

}
