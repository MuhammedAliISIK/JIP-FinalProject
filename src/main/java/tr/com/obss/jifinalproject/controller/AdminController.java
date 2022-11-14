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
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final BookService bookService;

    public AdminController(UserService userService, BookService bookService){
        this.userService = userService;
        this.bookService = bookService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(){
        LOGGER.info("A get request has been sent");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/by-username")
    public ResponseEntity<User> searchUsers(@RequestParam(name = "username",defaultValue = "") String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/all-by-username")
    public ResponseEntity<List<User>> searchAllUsers(@RequestParam(name = "username",defaultValue = "") String username){
        return ResponseEntity.ok(userService.findAllByUserName(username));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(name = "userId") long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUsername(@PathVariable(name = "userId") long id, @Valid @RequestBody UserUpdateDTO userDTO){
        return ResponseEntity.ok(userService.updateUsername(id, userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> removeUser(@PathVariable(name = "userId") long id){
        return ResponseEntity.ok(userService.remove(id));
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO){
        LOGGER.info("User info {} {}",userDTO.getUsername(),userDTO.getPassword());

        return ResponseEntity.ok(userService.save(userDTO));
    }

    @GetMapping("/library")
    public ResponseEntity<List<Book>> getBooks(){
        LOGGER.info("A get request has been sent");
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/library/search")
    public ResponseEntity<Book> searchBook(@RequestParam String searchName){
        return ResponseEntity.ok(bookService.getByName(searchName));
    }

    @GetMapping("/library/search-author")
    public ResponseEntity<List<Book>> searchAuthor(@RequestParam String searchAuthor){
        return ResponseEntity.ok(bookService.getByAuthor(searchAuthor));
    }

    @PostMapping("/library/add-book")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDTO bookDTO){
        LOGGER.info("Book info {} {}",bookDTO.getName(),bookDTO.getAuthor());
        return ResponseEntity.ok(bookService.saveBook(bookDTO));
    }

    @PutMapping("/library/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable long bookId,
                                           @Valid @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.update(bookId, bookDTO));
    }

    @DeleteMapping("/library/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable(name = "bookId") long bookId){
        return ResponseEntity.ok(bookService.deleteBookById(bookId));
    }
}
