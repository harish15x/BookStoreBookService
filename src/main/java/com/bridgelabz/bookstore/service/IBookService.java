package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.util.ResponseClass;

import java.util.List;

public interface IBookService {

    ResponseClass addBook(BookDTO bookDTO);

    ResponseClass updateBook(String token, BookDTO bookDTO, Long bookId);

    List<BookModel> getBookData(String token);

    ResponseClass deleteBook(long bookId, String token);

    ResponseClass changeBookQuantity(String token, long bookId, long quantity);
}
