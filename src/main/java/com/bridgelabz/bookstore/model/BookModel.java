package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="bookservice")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String booklogo;
    private String bookQuantity;
    private long bookPrice;
    private LocalDateTime registeredDate;

    public BookModel(BookDTO bookDTO){
        this.bookName = bookDTO.getBookName();
        this.bookAuthor = bookDTO.getBookAuthor();
        this.bookDescription = bookDTO.getBookDescription();
        this.booklogo = bookDTO.getBooklogo();
        this.bookQuantity = bookDTO.getBookQuantity();
    }

    public BookModel() {

    }
}
