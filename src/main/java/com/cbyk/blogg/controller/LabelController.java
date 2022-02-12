package com.cbyk.blogg.controller;

import com.cbyk.blogg.model.Label;
import com.cbyk.blogg.model.LabelDTO;
import com.cbyk.blogg.service.CacheService;
import com.cbyk.blogg.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("label")
public class LabelController {

    @Autowired
    LabelService labelService;

    @Autowired
    CacheService cacheService;

    @GetMapping("/getAll")
    public List<Label> getAllLabels(Model model) {
        return labelService.getAllActiveLabels();
    }

    @PostMapping("/create")
    public String createLabel(@RequestBody LabelDTO labelDTO) {
         labelService.createLabel(labelDTO);
        return "200";
    }

    @PostMapping("/update")
    public String updateLabel(@RequestBody LabelDTO labelDTO) {
        labelService.updateLabel(labelDTO);
        return "200";
    }

    @GetMapping("/cacheClear")
    public String cacheClear(@RequestParam(required = false) String cacheName){

        cacheService.evictAllCacheValues(cacheName);
        return "200";

    }


}
