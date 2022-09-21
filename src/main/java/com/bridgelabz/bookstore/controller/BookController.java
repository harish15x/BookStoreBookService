package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookservice")
public class BookController {

    @Autowired
    IBookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<ResponseClass> addBook(@RequestBody BookDTO bookDTO){
        ResponseClass responseClass = bookService.addBook(bookDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/update/{bookid}")
    public ResponseEntity<ResponseClass> updateBook(@RequestHeader String token, @RequestBody BookDTO bookDTO, @PathVariable long bookId){
        ResponseClass responseClass = bookService.updateBook(token, bookDTO, bookId);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<?>> getBookData(@RequestParam String token){
        List<BookModel> responseClass = bookService.getBookData(token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @DeleteMapping("/deletebook")
    public ResponseEntity<ResponseClass> deleteBook(@PathVariable long bookId, @RequestHeader String token){
        ResponseClass responseClass = bookService.deleteBook(bookId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PostMapping("/changebookquanity")
    public ResponseEntity<ResponseClass> changeBookQuantity(@RequestHeader String token,@PathVariable long bookId,@RequestParam long quantity){
        ResponseClass responseClass = bookService.changeBookQuantity(token, bookId, quantity);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }


}
