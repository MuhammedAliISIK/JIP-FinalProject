package tr.com.obss.jifinalproject.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Book{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PRESENCE")
    private boolean presence;
    @ManyToMany(mappedBy = "favorites")
    @JsonBackReference
    private Set<User> userFav;
    @ManyToMany(mappedBy = "readBooks")
    @JsonBackReference
    private Set<User> userRead;

    @PrePersist
    public void onPrePersist(){
        this.setPresence(true);
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return presence;
    }

}
