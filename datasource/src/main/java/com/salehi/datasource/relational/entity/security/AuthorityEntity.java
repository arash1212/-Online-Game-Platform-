package com.salehi.datasource.relational.entity.security;

import com.salehi.utility.constant.RelationalDBConstant;
import com.salehi.datasource.relational.interfaces.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "USER_AUTHORITY", schema = RelationalDBConstant.DEFAULT_SCHEMA, indexes = {
        @Index(name = "USER_AUTHORITY_IDX_ID", columnList = "ID")
})
public class AuthorityEntity implements IEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "userAuthority_sequence", sequenceName = "USER_AUTHORITY_SEQUENCE", schema = RelationalDBConstant.DEFAULT_SCHEMA,
            initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userAuthority_sequence")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CREATION_TIME", nullable = false)
    private ZonedDateTime creationTime;
    @Column(name = "AUTHORITY", unique = true, nullable = false, length = 100)
    private String authority;
    //TODO fix
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorities")
//    private Set<Users> users;

    @PrePersist
    private void setCreationTime() {
        this.creationTime = ZonedDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityEntity that = (AuthorityEntity) o;
        return id.equals(that.id) && creationTime.equals(that.creationTime) && authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationTime, authority);
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", authority='" + authority + '\'' +
                '}';
    }
}
