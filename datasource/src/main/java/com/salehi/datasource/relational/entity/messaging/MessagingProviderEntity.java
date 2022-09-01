package com.salehi.datasource.relational.entity.messaging;

import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.datasource.relational.interfaces.IEntity;
import com.salehi.utility.constant.RelationalDBConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "MESSAGING_PROVIDER", schema = RelationalDBConstant.DEFAULT_SCHEMA, indexes = {
        @Index(name = "MESSAGING_PROVIDER_IDX_ID", columnList = "ID")
})
public class MessagingProviderEntity implements IEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "messagingProvider_sequence", sequenceName = "MESSAGING_PROVIDER_SEQUENCE", schema = RelationalDBConstant.DEFAULT_SCHEMA,
            initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagingProvider_sequence")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "TITLE", length = 200, nullable = false)
    private String title;
    @Basic
    @Column(name = "ACTIVE", nullable = false)
    private boolean active;
    @Basic
    @Column(name = "CREATION_TIME", nullable = false)
    private ZonedDateTime creationTime;
    @Basic
    @Column(name = "DESCRIPTION", length = 500, nullable = false)
    private String description;
    @Basic
    @Column(name = "SERVICE_URL", length = 500, nullable = false)
    private String serviceUrl;
    @Basic
    @Column(name = "PORT", length = 50, nullable = true)
    private int port;
    @Basic
    @Column(name = "TOKEN_HEADR_NAME", length = 100, nullable = true)
    private String tokenHeaderName;
    @Basic
    @Column(name = "SERVICE_TOKEN", length = 500, nullable = true)
    private String serviceToken;
    @Basic
    @Column(name = "USERNAME", length = 200, nullable = true)
    private String username;
    @Basic
    @Column(name = "PASSWORD", length = 500, nullable = true)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "SUPPORTED_TYPE", nullable = false)
    private MessageTypeEnum supportedType;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "PROVIDER", nullable = false)
    private MessageProviderEnum provider;

    @PrePersist
    private void setCreationTime() {
        this.creationTime = ZonedDateTime.now();
        this.active = true;
    }
}
