package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.BlogPost;
import com.cbyk.blogg.model.User;
import com.cbyk.blogg.service.BlogService;
import com.cbyk.blogg.service.LabelService;
import com.cbyk.blogg.service.ResourceService;
import com.cbyk.blogg.util.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class BlogController {


    public static String bgimageurl="https://cbyk-bucket-test.s3.ap-southeast-1.amazonaws.com/blowg/bg2.jpg";

    public static SimpleGrantedAuthority adminAuthority=new SimpleGrantedAuthority("ROLE_Admin");

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
        boolean isAdmin=false;
        model.addAttribute("faker1", UUID.randomUUID().toString());
        model.addAttribute("faker2", ZonedDateTime.now().toInstant().toEpochMilli());
        model.addAttribute("bgurl", bgimageurl);
        model.addAttribute("labelNameCssMap",LabelService.labelNameCssMap);
//        model.addAttribute("isAdmin",false);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            if(((UserDetails) principal).getAuthorities().contains(adminAuthority)){
                isAdmin=true;
                model.addAttribute("isAdmin",isAdmin);
            }
        }
        List<BlogPost> blogPosts=isAdmin?blogService.fetchAllBlogs():blogService.fetchActiveBlogs();
        model.addAttribute("blogList", blogPosts);

        return "blog_list";
    }

    @GetMapping("/view/{faker1}/{uid}/{faker2}")
    public String viewBlog(Model model, @PathVariable String uid, @PathVariable String faker1, @PathVariable String faker2) {
        model.addAttribute("blogPost", blogService.getBlogById(uid));
        return "blogPostDetail";
    }

    @GetMapping("updatebg")
    public String updatebg(@RequestParam String imgName) {
        bgimageurl="https://cbyk-bucket-test.s3.ap-southeast-1.amazonaws.com/blowg/"+imgName;
        return "";
    }
}