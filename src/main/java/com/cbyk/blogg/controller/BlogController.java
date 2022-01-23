package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.service.BlogService;
import com.cbyk.blogg.util.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Controller
public class BlogController {


    @Autowired
    BlogService blogService;

    @GetMapping("/")
    public String greeting(Model model) {
        return "blog_list";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }


    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }


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
        model.addAttribute("blogPost", blogPost);
        return "data_template";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("faker1", UUID.randomUUID().toString());
        model.addAttribute("faker2",  ZonedDateTime.now().toInstant().toEpochMilli());
        model.addAttribute("blogList", blogService.fetchAllBlogs());
        return "blog_list";
    }

    @GetMapping("/view/{faker1}/{uid}/{faker2}")
    public String viewBlog(Model model,@PathVariable String uid,@PathVariable String faker1,@PathVariable String faker2) {
        model.addAttribute("blogPost", blogService.getBlogById(uid));
        return "blogPostDetail";
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