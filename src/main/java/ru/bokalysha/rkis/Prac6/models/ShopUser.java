package ru.bokalysha.rkis.Prac6.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая пользователя магазина.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class ShopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @NotEmpty
    @Column(name = "u_password")
    private String password;

    @Column(name = "u_role")
    private String role;
}

