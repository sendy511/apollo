package com.eking.apollo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/readinglist")
public class ReadingListController {

    @RequestMapping("/{reader}")
    public String getList(@PathVariable("reader") String reader, Model model){
        return "readinglist";
    }
}
