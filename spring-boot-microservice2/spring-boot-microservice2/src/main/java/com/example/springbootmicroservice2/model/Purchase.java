package com.example.springbootmicroservice2.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "purchase")
@EntityListeners(AuditingEntityListener.class)
public class Purchase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "course_id", nullable = false)
    private Long courseId;
    @Column(name = "total", nullable = false)
    private Long total;
    @Column(name = "purchase_time", nullable = false)
    private LocalDateTime purchaseTime;
    @PrePersist
    protected void onCreate() {
        purchaseTime = LocalDateTime.now();
    }
}
