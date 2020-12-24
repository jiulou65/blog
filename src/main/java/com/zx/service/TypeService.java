package com.zx.service;

import com.zx.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    /**
     * 保存分类
     * @param type
     * @return
     */
    public Type saveType(Type type);

    /**
     * 查询分类
     * @param id
     * @return
     */
    public Type getType(Long id);

    public Type getTypeByName(String name);

    /**
     * 展示分类
     * @param pageable
     * @return
     */
    public Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    /**
     * 更新分类
     * @param id
     * @param type
     * @return
     */
    public Type updateType(Long id, Type type);

    /**
     * 删除分类
     * @param id
     * @return
     */
    public void deleteType(Long id);

}
