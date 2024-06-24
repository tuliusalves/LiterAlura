package com.tulius.literAlura.dto;

public record BooksDTO (Long id,
                        String title,
                        String [] languages,
                        Integer download_content,
                        String image){
}
