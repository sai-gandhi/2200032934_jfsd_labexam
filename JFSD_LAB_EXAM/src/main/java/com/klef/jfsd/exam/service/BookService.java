package com.klef.jfsd.exam.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.exam.exception.ResourceNotFoundException;
import com.klef.jfsd.exam.model.Book;
import com.klef.jfsd.exam.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
            .map(book -> {
                book.setTitle(updatedBook.getTitle());
                book.setName(updatedBook.getName());
                book.setGenre(updatedBook.getGenre());
                book.setPrice(updatedBook.getPrice());
                book.setYear(updatedBook.getYear());
                return bookRepository.save(book);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
}
