package tr.com.obss.jifinalproject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public class EntityBase {
    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "OPERATION_TYPE")
    private String operationType;
    @Column(name = "ACTIVE")
    private boolean active;

    @PrePersist
    public void onPrePersist(){
        this.setActive(true);
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
        this.setOperationType("SAVE");
    }

    @PreUpdate
    public void onPreUpdate(){
        this.setUpdateDate(new Date());
        this.setOperationType("UPDATE");
    }

    @PreRemove
    public void onPreRemove(){
        this.setUpdateDate(new Date());
        this.setOperationType("DELETE");
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getOperationType() {
        return operationType;
    }

    public boolean isActive() {
        return active;
    }
}
