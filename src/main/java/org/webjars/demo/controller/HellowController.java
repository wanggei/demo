package org.webjars.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.demo.model.Book;
import org.webjars.demo.service.BookService;

import java.util.List;

@Controller
public class HellowController {


    @Autowired
    BookService bookService;
    @RequestMapping(value = "/")
    public String hell(Model model){
        List<Book> all = bookService.getAll();
        model.addAttribute("books",all);
        return "qihangke";
    }
    @RequestMapping(value = "/login.html")
    public String login(){
        return "login";
    }

//    @ResponseBody
//    /*@RequestMapping(value = "/person/{id}")
//    public Person getone(@PathVariable("id") Integer id){
//
//        return personDao.getOne(id);
//    }*/
}
