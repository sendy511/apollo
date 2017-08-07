package com.eking.apollo.controllers;


import com.eking.apollo.model.Reading;
import com.eking.apollo.repository.ReadlingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/readinglist")
public class ReadingListController {

    @Autowired
    private ReadlingListRepository readlingListRepository;

    @RequestMapping("/{reader}")
    public String getList(@PathVariable("reader") String reader, Model model){
        List<Reading> byReader = readlingListRepository.findByReader(reader);
        model.addAttribute("readinglist", byReader);
        return "readinglist";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addList(Reading reading){
        System.out.println("test");
        readlingListRepository.save(reading);
        return "redirect:/readinglist/{reader}";
    }
}
