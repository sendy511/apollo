package com.eking.apollo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/readingList")
public class ReadingListController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping("/{reader}")
    public String readerBooks(@PathVariable("reader") String reader, Model model){
        List<Book> books = this.readingListRepository.findByReader(reader);
        model.addAttribute("books", books);

        return "readingList";
    }
}
