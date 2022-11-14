package tr.com.obss.jifinalproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS_LIST")
public class User extends EntityBase{
    @Column(name = "USERNAME",length = 255, unique = true)
    private String username;
    @Column(name = "PASSWORD",length = 255)
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER )
    @JoinTable(name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER )
    @JoinTable(name = "READ_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private Set<Book> readBooks;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER )
    @JoinTable(name = "FAVORITE_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private Set<Book> favorites;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addFavorite(Book favorite){
        favorites.add(favorite);
    }

    public void removeFavorite(Book book){
        favorites.remove(book);
    }

    public void addReadBooks(Book readBook) {
        readBooks.add(readBook);
    }

    public void removeReadBooks(Book book){
        readBooks.remove(book);
    }

    public Set<Book> getFavorites() {
        return favorites;
    }

    public Set<Book> getReadBooks() {
        return readBooks;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}