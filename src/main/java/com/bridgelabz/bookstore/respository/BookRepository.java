package com.bridgelabz.bookstore.respository;

import com.bridgelabz.bookstore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {
}
