package com.zx.web.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.zx.po.User;
import com.zx.service.UserService;
import com.zx.utils.CookieUtils;
import com.zx.utils.JwtUtils;
import com.zx.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private Producer captchaProducer;

    @GetMapping()
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String verifyCode,
                        HttpSession session,
                        RedirectAttributes attributes, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {

        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (code == null || !code.equals(verifyCode)){
            attributes.addFlashAttribute("message","验证码错误!");
            return "redirect:/admin";
        }
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user != null){
            String token = JwtUtils.createJWT(user);
            Cookie cookie = CookieUtils.createCookie("token", token, "/admin",30);
            ObjectMapper objectMapper = new ObjectMapper();
            user.setPassword(null);
            String u = objectMapper.writeValueAsString(user);
            String encode = URLEncoder.encode(u, "utf-8");
            Cookie cookie2 = CookieUtils.createCookie("user", encode, "/",30);
            response.addCookie(cookie);
            response.addCookie(cookie2);
        } else {
            attributes.addFlashAttribute("message","用户名和密码错误!");
            return "redirect:/admin";
        }
        return "admin/index";
    }


    @GetMapping("/getKaptchaImage")
    public void getKaptchaImage(HttpServletResponse response, HttpSession session) throws Exception {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        //将验证码存到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
