package tr.com.obss.jifinalproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.obss.jifinalproject.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.id = :id")
    Optional<Book> getBookById(long id);
    @Query("select b from Book b where b.name = :name")
    Optional<Book> getBookByName(String name);
    @Query("select b from Book b where b.author = :author")
    List<Book> getBookByAuthor(String author);
    @Query("select b from Book b where b.presence = true")
    List<Book> getBookByPresence();
}
