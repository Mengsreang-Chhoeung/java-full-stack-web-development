package com.ms.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @GetMapping("/one/{id}/name/{name}")
    public String getBook(@PathVariable("name") String bookName, @PathVariable("id") Long bookId) {
        return "Book ID: " + bookId + " and Name: " + bookName;
    }

    @GetMapping({"/two", "/two/{id}"})
    public String getBook2(@PathVariable(value = "id", required = false) Long bookId) {
        return "Book ID: " + bookId;
    }

    @GetMapping({"/three", "/three/{id}"})
    public String getBook3(@PathVariable(value = "id") Optional<Long> bookId) {
        if (bookId.isPresent()) {
            return "Book ID: " + bookId.get();
        } else {
            return "ID is missing";
        }
    }
}
