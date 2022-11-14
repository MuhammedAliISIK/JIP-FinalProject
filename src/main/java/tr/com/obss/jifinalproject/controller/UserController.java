package tr.com.obss.jifinalproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jifinalproject.entity.Book;
import tr.com.obss.jifinalproject.entity.User;
import tr.com.obss.jifinalproject.model.BookDTO;
import tr.com.obss.jifinalproject.model.UserDTO;
import tr.com.obss.jifinalproject.model.UserUpdateDTO;
import tr.com.obss.jifinalproject.service.BookService;
import tr.com.obss.jifinalproject.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    private final UserService userService;
    private final BookService bookService;

    public UserController(UserService userService, BookService bookService){
        this.userService = userService;
        this.bookService = bookService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/library")
    public ResponseEntity<List<Book>> getBooks(){
        LOGGER.info("A get request has been sent");
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/library/present-books")
    public ResponseEntity<List<Book>> getPresentBooks(){
        LOGGER.info("A get request has been sent");
        return ResponseEntity.ok(bookService.findPresent());
    }

    @GetMapping("/library/search")
    public ResponseEntity<Book> searchBookByName(@RequestParam String searchName){
        return ResponseEntity.ok(bookService.getByName(searchName));
    }

    @GetMapping("/library/search-author")
    public ResponseEntity<List<Book>> searchAuthor(@RequestParam String searchAuthor){
        return ResponseEntity.ok(bookService.getByAuthor(searchAuthor));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updatePassword(@PathVariable(name = "userId") long id, @Valid @RequestBody UserUpdateDTO userDTO){
        return ResponseEntity.ok(userService.updatePassword(id, userDTO));
    }

    @PostMapping("/library/fav-list/{userId}")
    public ResponseEntity<?> favoriteBook(@PathVariable(name = "userId") long userId, @RequestParam String bookName){
        return ResponseEntity.ok(userService.addFavoriteBook(userId,bookName));
    }

    @PostMapping("/library/fav-list-remove/{userId}")
    public ResponseEntity<?> removeFavoriteBook(@PathVariable(name = "userId") long userId, @RequestParam String bookName){
        return ResponseEntity.ok(userService.removeFavoriteBook(userId,bookName));
    }

    @PostMapping("/library/read-list/{userId}")
    public ResponseEntity<?> readBook(@PathVariable(name = "userId") long userId, @RequestParam String bookName){
        return ResponseEntity.ok(userService.addReadBook(userId,bookName));
    }

    @PostMapping("/library/read-list-remove/{userId}")
    public ResponseEntity<?> removeReadBook(@PathVariable(name = "userId") long userId, @RequestParam String bookName){
        return ResponseEntity.ok(userService.removeReadBook(userId,bookName));
    }

}