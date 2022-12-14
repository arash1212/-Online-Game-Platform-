package com.salehi.datasource.relational.entity.security;

import com.salehi.datasource.relational.interfaces.IEntity;
import com.salehi.utility.constant.RelationalDBConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER_AUTHORITY", schema = RelationalDBConstant.DEFAULT_SCHEMA, indexes = {
        @Index(name = "USER_AUTHORITY_IDX_ID", columnList = "ID")
})
public class SecurityAuthorityEntity implements GrantedAuthority, IEntity {
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
}
