package tr.com.obss.jifinalproject.service;

import org.springframework.stereotype.Service;
import tr.com.obss.jifinalproject.entity.Book;
import tr.com.obss.jifinalproject.entity.User;
import tr.com.obss.jifinalproject.model.BookDTO;
import tr.com.obss.jifinalproject.repo.BookRepository;
//import tr.com.obss.jifinalproject.repo.FavoriteRepository;
import tr.com.obss.jifinalproject.repo.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public List<Book> findPresent(){
        return bookRepository.getBookByPresence();
    }
    public Book saveBook(BookDTO bookDTO){
        var book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());

        return bookRepository.save(book);
    }

    public Book getByName(String name){
        var bookOpt = bookRepository.getBookByName(name);
        return bookOpt.orElseThrow(() -> {
            throw new IllegalArgumentException("Book not found!");
        });
    }

    public List<Book> getByAuthor(String author){
        var bookSearch = bookRepository.getBookByAuthor(author);
        return bookSearch;
    }
    public Book getBookById(long bookId){
        var bookOpt = bookRepository.getBookById(bookId);
        return bookOpt.orElseThrow(() -> {
            throw new IllegalArgumentException("Book not found!");
        });
    }

    public Book deleteBookById(long bookId){
        var book = this.getBookById(bookId);
        book.setPresence(!book.isPresent());
        return bookRepository.save(book);
    }

    public Book update(long bookId, BookDTO bookDTO){
        var book = this.getBookById(bookId);
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        return bookRepository.save(book);
    }



}
