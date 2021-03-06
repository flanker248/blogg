package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.service.BlogService;
import com.cbyk.blogg.service.LabelService;
import com.cbyk.blogg.service.MyUserDetailsService;
import com.cbyk.blogg.util.EntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    BlogService blogService;

    @Autowired
    LabelService labelService;

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
    public String createBlog(Model model) {
//        String data= TestData.firstTestBlog;
//        BlogPost blogPost =new BlogPost("Heading","subheading",data,"Chirag Bhasin","17/03/21");
//        model.addAttribute("blog", blogPost);
        model.addAttribute("bgurl", BlogController.bgimageurl);
        model.addAttribute("labels",labelService.getAllActiveLabels());

        return "create_blog";
    }

    @PostMapping(value = "/saveBlog", consumes = "application/x-www-form-urlencoded;charset=UTF-8",
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String saveBlog(@RequestParam Map<String, Object> request,@RequestParam("labels") List<String> labels) {
        BlogPost blog = new BlogPost(request, EntityStatus.ACTIVE);
        blog.labels=labels;
        blogService.saveBlog(blog);
        return "redirect:/list";
    }


    @GetMapping("/rmv-blg")
    public String rmvblg(@RequestParam String uid) {
        blogService.inactivateBlog(uid);
        return "redirect:list";

    }


}