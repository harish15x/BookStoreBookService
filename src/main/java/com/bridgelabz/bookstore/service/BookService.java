package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.exception.BookNotFoundException;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.respository.BookRepository;
import com.bridgelabz.bookstore.util.ResponseClass;
import com.bridgelabz.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseClass addBook(BookDTO bookDTO) {
        BookModel bookModel = new BookModel(bookDTO);
        bookModel.setRegisteredDate(LocalDateTime.now());
        bookRepository.save(bookModel);
        return new ResponseClass(200, "Sucessfull", bookModel);
    }

    @Override
    public ResponseClass updateBook(String token, BookDTO bookDTO, Long bookId) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isBookPresent){
            Optional<BookModel> isUserAvailable = bookRepository.findById(bookId);
            if (isUserAvailable.isPresent()){
                isUserAvailable.get().setBookName(bookDTO.getBookName());
                isUserAvailable.get().setBookAuthor(bookDTO.getBookAuthor());
                isUserAvailable.get().setBookDescription(bookDTO.getBookDescription());
                isUserAvailable.get().setBooklogo(bookDTO.getBooklogo());
                isUserAvailable.get().setBookQuantity(bookDTO.getBookQuantity());
                bookRepository.save(isUserAvailable.get());
                return new ResponseClass(200, "Sucessfull", isUserAvailable.get());
            }
            throw new BookNotFoundException(400, "token is wrong");
        }
        throw new BookNotFoundException(400, "Book not found");
    }

    @Override
    public List<BookModel> getBookData(String token) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if(isBookPresent){
            List<BookModel> isBookAvailable = bookRepository.findAll();
            if (isBookAvailable.size() > 0)
                return isBookAvailable;
        }
        throw new BookNotFoundException(400,"token is wrong");
    }

    @Override
    public ResponseClass deleteBook(long bookId, String token) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isBookPresent){
            Optional<BookModel> isBookAvailable = bookRepository.findById(bookId);
            if (isBookAvailable.isPresent()){
                bookRepository.delete(isBookAvailable.get());
                return new ResponseClass(200, "Sucessfull",isBookAvailable.get());
            }
            throw new BookNotFoundException(400,"token is wrong");
        }
        throw new BookNotFoundException(400, "user not found");
    }

    @Override
    public ResponseClass changeBookQuantity(String token, long bookId, long quantity) {
        boolean isBookPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if(isBookPresent){
            Optional<BookModel> isBookAvailable = bookRepository.findById(bookId);
            if (isBookAvailable.isPresent()){
                isBookAvailable.get().equals(quantity);
                bookRepository.save(isBookAvailable.get());
            }
            throw new BookNotFoundException(400, "token is wrong");
        }
        throw new BookNotFoundException(400, "book not found");
    }


}
