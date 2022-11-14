package tr.com.obss.jifinalproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obss.jifinalproject.entity.User;
import tr.com.obss.jifinalproject.model.BookDTO;
import tr.com.obss.jifinalproject.model.MyUserDetails;
import tr.com.obss.jifinalproject.model.UserDTO;
import tr.com.obss.jifinalproject.model.UserUpdateDTO;
import tr.com.obss.jifinalproject.repo.BookRepository;
import tr.com.obss.jifinalproject.repo.RoleRepository;
import tr.com.obss.jifinalproject.repo.UserDAO;
import tr.com.obss.jifinalproject.repo.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.findByUsername(username);
        return new MyUserDetails(user);
    }
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final UserDAO userDAO;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserDAO userDAO, RoleRepository roleRepository,
                       BookRepository bookRepository, PasswordEncoder encoder){
        this.userDAO = userDAO;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
        this.encoder = encoder;
    }

    public User save(UserDTO userDTO){
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        var userRoleOpt = roleRepository.findByName("ROLE_USER");

        userRoleOpt.ifPresent((userRole) -> {
            user.setRoles(Set.of(userRoleOpt.get()));
        });
        return userRepository.save(user);
    }

    public User addFavoriteBook(long id, String bookName){
        var user = userRepository.getUserById(id);
        var bookFavOpt = bookRepository.getBookByName(bookName);
        bookFavOpt.ifPresent((bookFav) -> {
            user.get().addFavorite(bookFav);
        });
        return userRepository.save(user.get());
    }

    public User removeFavoriteBook(long id, String bookName){
        var user = userRepository.getUserById(id);
        var bookRemoveOpt = bookRepository.getBookByName(bookName);
        bookRemoveOpt.ifPresent((bookRemove) -> {
            user.get().removeFavorite(bookRemove);
        });
        return userRepository.save(user.get());
    }

    public User addReadBook(long id, String bookName){
        var user = userRepository.getUserById(id);
        var bookReadOpt = bookRepository.getBookByName(bookName);
        bookReadOpt.ifPresent((bookRead) -> {
            user.get().addReadBooks(bookRead);
        });
        return userRepository.save(user.get());
    }

    public User removeReadBook(long id, String bookName){
        var user = userRepository.getUserById(id);
        var bookReadOpt = bookRepository.getBookByName(bookName);
        bookReadOpt.ifPresent((bookRead) -> {
            user.get().removeReadBooks(bookRead);
        });
        return userRepository.save(user.get());
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> getUsersWithRole(List<String> roles){
        return userRepository.findByRoles_NameIn(roles);
    }

    public Page<User> findAllWithPagination(int pageNumber, int pageSize){
        var paged = PageRequest.of(pageNumber,pageSize);
        return userRepository.findAll(paged);
    }

    public List<User> findAllDaoPagination(int pageNumber, int pageSize){
        return userDAO.get(pageNumber,pageSize);
    }

    public User findByUsername(String username){
        var userOpt = userRepository.findByUsername(username);
        return userOpt.orElseThrow(() -> {
            throw new IllegalArgumentException("User not found!");
        });
    }

    public User findById(long id){
        var userOpt = userRepository.findById(id);
        return userOpt.orElseThrow(() -> {
            throw new IllegalArgumentException("User not found!");
        });
    }

    public List<User> findAllByUserName(String username){
        return userRepository.findByUsernameStartsWithAndActiveTrueOrderByCreateDateDesc(username);
    }

    public User getById(long id){
        var userOpt = userRepository.getUserById(id);
        return userOpt.orElseThrow(() -> {
            throw new IllegalArgumentException("User not found!");
        });
    }

    public User getByIdNative(long id){
        var userOpt = userRepository.getByIdNative(id);
        return userOpt.orElseThrow(() -> {
            throw new IllegalArgumentException("User not found!");
        });
    }

    public User updatePassword(long id, UserUpdateDTO dto){
        var user = this.findById(id);
        user.setPassword(encoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }
    public User updateUsername(long id, UserUpdateDTO dto){
        var user = this.findById(id);
        user.setUsername(dto.getUsername());
        return userRepository.save(user);
    }

    public User remove(long id){
        var user = this.findById(id);
        user.setActive(!user.isActive());
        return userRepository.save(user);
    }


}