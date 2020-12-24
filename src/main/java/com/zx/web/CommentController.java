package com.zx.web;

import com.zx.po.Comment;
import com.zx.po.User;
import com.zx.service.BlogService;
import com.zx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: zzx
 * @Date: 2020-06-14 15:49
 * @Version 1.0
 * @描述：
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{id}")
    public String comments(@PathVariable("id") String blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(Long.valueOf(blogId)));
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (comment.getBlog().getUser().getId().equals(user.getId())){
                comment.setAdminComment(true);
            }
            comment.setAvatar(user.getAvatar());
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
