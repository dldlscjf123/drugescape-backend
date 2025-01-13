package com.lee.drugescape.drugescape.entity;

import com.lee.drugescape.drugescape.type.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column(columnDefinition = "ENUM('GOOGLE') DEFAULT 'GOOGLE'")
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
