package tr.com.obss.jifinalproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role extends EntityBase{
    @Column(name ="NAME", length = 255, unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> users;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

