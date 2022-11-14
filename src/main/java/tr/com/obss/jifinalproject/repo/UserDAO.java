package tr.com.obss.jifinalproject.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.com.obss.jifinalproject.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private EntityManager entityManager;

    public UserDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<User> get(int pageNumber, int pageSize){
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var from = criteriaQuery.from(User.class);
        var select = criteriaQuery.select(from);
        select.where(criteriaBuilder.isTrue(from.get("active")));
        var typedQuery = entityManager.createQuery(select);

        typedQuery.setFirstResult(pageNumber*pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }
}