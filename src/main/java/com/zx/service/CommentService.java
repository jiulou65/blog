package com.zx.service;

import com.zx.po.Comment;
import java.util.List;

/**
 * @Author: zzx
 * @Date: 2020-06-14 15:47
 * @Version 1.0
 * @描述：
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
