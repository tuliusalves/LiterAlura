package com.tulius.literAlura.controller;

import com.tulius.literAlura.dto.BooksDTO;
import com.tulius.literAlura.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    private BooksService service;

    @GetMapping("/books")
    public List<BooksDTO> getBook(){
        return service.getAllBooks();
    }
}
