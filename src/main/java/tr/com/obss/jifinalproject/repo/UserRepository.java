package tr.com.obss.jifinalproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.obss.jifinalproject.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    List<User> findByUsernameStartsWithAndActiveTrueOrderByCreateDateDesc(String username);
    List<User> findByRoles_NameIn(List<String> roles);

    @Query("select u from User u where u.id = :id")
    Optional<User> getUserById(long id);

    @Query(value = "select * from USERS_LIST where ID = :id",nativeQuery = true)
    Optional<User> getByIdNative(long id);
}