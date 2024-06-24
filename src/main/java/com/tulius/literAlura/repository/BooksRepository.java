package com.tulius.literAlura.repository;

import com.tulius.literAlura.model.Authors;
import com.tulius.literAlura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository <Books, Long>{
    List<Books> findByTitleContainingIgnoreCase(String bookName);

    @Query("SELECT a FROM Books b JOIN b.athors a ORDER BY name")
    List<Authors> findAuthors();

    @Query(value = "SELECT * FROM public.books ORDER BY languages", nativeQuery = true)
    List<Books> findAllBooksOrderByLanguages();

    List<Books> findTop5ByOrderByDownloadCountDesc();
}
