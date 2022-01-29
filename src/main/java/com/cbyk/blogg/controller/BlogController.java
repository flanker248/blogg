package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.service.BlogService;
import com.cbyk.blogg.service.ResourceService;
import com.cbyk.blogg.util.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Controller
public class BlogController {


    public static String bgimageurl="https://cbyk-bucket-test.s3.ap-southeast-1.amazonaws.com/blowg/bg3.jpg";

    @Autowired
    BlogService blogService;

    @Autowired
    ResourceService resourceService;

    @GetMapping("/")
    public String greeting(ModelMap model) {
        return "redirect:list";

    }

    @GetMapping(value = "/res", produces = MediaType.IMAGE_JPEG_VALUE)
    public void res(HttpServletResponse response, @RequestParam String rn, String tp) throws IOException {
        ClassPathResource imgFile = resourceService.fetchResource(rn, tp);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("bgurl", bgimageurl);
        return "about";
    }


    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("bgurl", bgimageurl);
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
        model.addAttribute("faker2", ZonedDateTime.now().toInstant().toEpochMilli());
        model.addAttribute("blogList", blogService.fetchAllBlogs());
        model.addAttribute("bgurl", bgimageurl);
        return "blog_list";
    }

    @GetMapping("/view/{faker1}/{uid}/{faker2}")
    public String viewBlog(Model model, @PathVariable String uid, @PathVariable String faker1, @PathVariable String faker2) {
        model.addAttribute("blogPost", blogService.getBlogById(uid));
        return "blogPostDetail";
    }

    @GetMapping("/tests")
    public String tests(Model model) {
        blogService.fetchAllBlogs().stream().forEach(b -> {
            System.out.println(b.id);
        });

        BlogPost bp = blogService.getBlogById("61e5b56f181931584313efce");
        System.out.println("bp: " + bp.title);


        BlogPost blogPost = new BlogPost("title 7", "<b>BODY of blog 7</b>");
        blogService.saveBLog(blogPost);
        return "redirect:list";

    }

    @GetMapping("updatebg")
    public String updatebg(@RequestParam String imgName) {
        bgimageurl="https://cbyk-bucket-test.s3.ap-southeast-1.amazonaws.com/blowg/"+imgName;
        return "";
    }

    // change about and contact bg url code to match list page code
    // updatebg to be secured
}