package com.book.controller;

import com.book.data.BookRepository;
import com.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    private static final String reader = "craig";

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Model model) {
        List<Book> bookList = bookRepository.findByReader(reader);
        if (bookList != null) {
            model.addAttribute("books", bookList);
        }
        return "bookList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToBookList(Book book) {
        book.setReader(reader);
        bookRepository.save(book);
        return "redirect:/";
    }

}
