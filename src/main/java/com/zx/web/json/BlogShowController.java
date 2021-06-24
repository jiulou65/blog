package com.zx.web.json;

import antlr.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zx.config.ApplicationContextProvider;
import com.zx.po.Blog;
import com.zx.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@RestController
public class BlogShowController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/getBlog")
    public String getBlogs(String keyWord) throws JsonProcessingException {
        List<Blog> blogs = new ArrayList<>();
        if (keyWord!=null && !keyWord.equals("")){
            // 搜索博客
            blogs = blogService.findBlogByKeyWord(keyWord);
        } else {
            // 全部博客
            blogs = blogService.findAllBlog();
        }
        ObjectMapper mapper = new ObjectMapper();
        String blogJson = mapper.writeValueAsString(blogs);
        return blogJson;
    }


    private static Integer num = 0;
    private static Logger logger = LoggerFactory.getLogger(BlogShowController.class);

    @RequestMapping("/myTest")
    public String test(){
        Executor executor = ApplicationContextProvider.getBean("getAsyncExecutor",Executor.class);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(num++);
                logger.info("test:"+Thread.currentThread().getName());
            }
        });
        return num+"";
    }

}
