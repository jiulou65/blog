package com.zx.dao;

import com.zx.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true and b.published = true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from  Blog b where b.published = true and (b.title like ?1 or b.content like ?1)")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year DESC ")
    List<String> findGroupByYears();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') =?1 ")
    List<Blog> findByYear(String year);

    // 查找指定博客
    @Query("select b from Blog b where b.id = ?1 and b.published = true ")
    Blog findOneBlog(Long id);
}
