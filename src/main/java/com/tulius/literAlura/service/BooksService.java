package com.tulius.literAlura.service;

import com.tulius.literAlura.dto.BooksDTO;
import com.tulius.literAlura.model.Books;
import com.tulius.literAlura.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {
    @Autowired
    private BooksRepository repository;

    private List<BooksDTO> convertData(List<Books> books){
        return books.stream()
                .map(b -> new BooksDTO(b.getId(),
                        b.getTitle(),
                        b.getLanguages(),
                        b.getDownloadCount(),
                        b.getImage()))
                .collect(Collectors.toList());
    }
    public List<BooksDTO> getAllBooks(){
        return convertData(repository.findAll());
    }


}
