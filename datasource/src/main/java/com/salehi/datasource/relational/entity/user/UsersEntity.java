package com.salehi.datasource.relational.entity.user;

import com.salehi.datasource.relational.entity.security.AuthorityEntity;
import com.salehi.datasource.relational.interfaces.IEntity;
import com.salehi.utility.constant.RelationalDBConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS", schema = RelationalDBConstant.DEFAULT_SCHEMA, indexes = {
        @Index(name = "USERS_IDX_ID", columnList = "ID")
})
public class UsersEntity implements IEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "USERS_SEQUENCE", schema = RelationalDBConstant.DEFAULT_SCHEMA,
            initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CREATION_TIME", nullable = false)
    private ZonedDateTime creationTime;
    @Basic
    @Column(name = "EMAIL", nullable = false, unique = true, length = 150)
    private String email;
    @Basic
    @Column(name = "MOBILE", nullable = false, unique = true, length = 30)
    private String mobile;
    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    @Basic
    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
    @Basic
    @Column(name = "ACCOUNT_NAME", nullable = false, unique = true, length = 150)
    private String accountName;
    @Basic
    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
    private boolean accountNonExpired;
    @Basic
    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
    private boolean accountNonLocked;
    @Basic
    @Column(name = "CREDENTIALS_NON_EXPIRED", nullable = false)
    private boolean credentialsNonExpired;
    @Basic
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;
    @Basic
    @Column(name = "EMAIL_CONFIRMATION_DATE")
    private ZonedDateTime emailConfirmationDate;
    @Basic
    @Column(name = "MOBILE_CONFIRMATION_DATE")
    private ZonedDateTime mobileConfirmationDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USERS_AUTHORITY", schema = RelationalDBConstant.DEFAULT_SCHEMA,
            joinColumns = @JoinColumn(name = "USERS_ID_FK", referencedColumnName = "ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID_FK", referencedColumnName = "ID", nullable = false),
            indexes = {
                    @Index(name = "USER_AUTHORITY_IDX_USER", columnList = "USERS_ID_FK"),
                    @Index(name = "USER_AUTHORITY_IDX_AUTHORITY", columnList = "AUTHORITY_ID_FK")
            }
    )
    private Set<AuthorityEntity> authorities;

    @PrePersist
    private void prePersist() {
        this.deleted = false;
        this.credentialsNonExpired = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.creationTime = ZonedDateTime.now();
        this.emailConfirmationDate = null;
        this.mobileConfirmationDate = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity entity = (UsersEntity) o;
        return id.equals(entity.id) && creationTime.equals(entity.creationTime) && email.equals(entity.email) && mobile.equals(entity.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationTime, email, password, deleted, accountName, accountNonExpired,
                accountNonLocked, credentialsNonExpired, enabled, authorities);
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", deleted=" + deleted +
                ", accountName='" + accountName + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", emailConfirmationDate=" + emailConfirmationDate +
                ", mobileConfirmationDate=" + mobileConfirmationDate +
                ", authorities=" + authorities +
                '}';
    }
}
