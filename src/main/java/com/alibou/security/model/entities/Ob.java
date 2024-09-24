package com.alibou.security.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Ob {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer price;
    private String brand;
    private String pictureURL;
    @Column(length = 10485760)
    private String description;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private Date createDate;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Integer createdByUserid;
}
