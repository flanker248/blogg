package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.service.BlogService;
import com.cbyk.blogg.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    BlogService blogService;

    @Autowired
    MyUserDetailsService userService;



//    @PreAuthorize("hasAuthority('Admin')")
//    @GetMapping("/testpa")
//    public String testpa() {
//        return "blog_saved";
//    }
//
//
//    @PreAuthorize("hasAuthority('Admin')")
//    @GetMapping("/testpb")
//    public String testpb() {
//        return "blog_saved";
//    }



    @GetMapping("/create-b")
    public String createBlog() {
//        String data= TestData.firstTestBlog;
//        BlogPost blogPost =new BlogPost("Heading","subheading",data,"Chirag Bhasin","17/03/21");
//        model.addAttribute("blog", blogPost);
        return "create_blog";
    }

    @PostMapping(value = "/saveBlog", consumes = "application/x-www-form-urlencoded;charset=UTF-8",
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String saveBlog(@RequestParam Map<String, String> request) {
        BlogPost blog = new BlogPost(request.get("title"), request.get("blogBody"));
        blogService.saveBlog(blog);
        return "redirect:list";
    }


    @GetMapping("/rmv-blg")
    public String rmvblg(@RequestParam String uid) {
        blogService.removeBlog(uid);
        return "redirect:list";

    }


}