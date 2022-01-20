package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.model.BlogRequest;
import com.cbyk.blogg.service.BlogService;
import com.cbyk.blogg.util.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Controller
public class AdminController {


    @Autowired
    BlogService blogService;

    @GetMapping("/post/{uuid}")
    public String greeting(@PathVariable String uuid, Model model) {
        model.addAttribute("name", uuid);
        System.out.println("uuid: " + uuid);
        return "blog_template";
    }

    @GetMapping("/datatest")
    public String datatest(Model model) {
        String data = TestData.firstTestBlog;
        BlogPost blogPost = new BlogPost("Heading", "subheading", data, "Chirag Bhasin", "17/03/21");
        model.addAttribute("blog", blogPost);
        return "data_template";
    }

    @GetMapping("/create-b")
    public String createBlog() {
//        String data= TestData.firstTestBlog;
//        BlogPost blogPost =new BlogPost("Heading","subheading",data,"Chirag Bhasin","17/03/21");
//        model.addAttribute("blog", blogPost);
        return "create_blog";
    }

    @PostMapping(value = "/saveBlog", consumes = "application/x-www-form-urlencoded;charset=UTF-8",
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String saveBlog( @RequestParam Map<String, String> request) {
        BlogPost blog = new BlogPost(request.get("title"),request.get("blogBody"));
        blogService.saveBLog(blog);
        return "blog_saved";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("blogList", blogService.fetchAllBlogs());
        return "blog_list";
    }

    @GetMapping("/tests")
    public String tests(Model model) {
        blogService.fetchAllBlogs().stream().forEach(b->{
            System.out.println(b.id);
        });

        BlogPost bp=blogService.getBlogById("61e5b56f181931584313efce");
        System.out.println("bp: "+bp.title);


        BlogPost blogPost=new BlogPost("title 7","<b>BODY of blog 7</b>");
        blogService.saveBLog(blogPost);
        return "blog_saved";


        //
        // added spring security dep
        // Auditing code implemented : restructuring + auditing


    }

}