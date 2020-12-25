package com.zx.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zx.po.Comment;
import com.zx.po.User;
import com.zx.service.BlogService;
import com.zx.service.CommentService;
import com.zx.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

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
    public String post(Comment comment, HttpSession session, HttpServletRequest request) throws IOException {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        // User user = (User) session.getAttribute("user");
        Cookie cookie = CookieUtils.getCookie(request, "user");
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        if (cookie != null){
            user = mapper.readValue(URLDecoder.decode(cookie.getValue(),"utf-8"), User.class);
        }
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
