package com.example.foode.User;

import com.example.foode.Fridge.Fridge;
import com.example.foode.Offer.Offer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "\"USER\"")
@Data
public class User {

    @Id
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_id_seq")
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal points;

    @Column(nullable = false)
    private String contact;

    @OneToOne(mappedBy = "user")
    private Fridge fridge;

    @OneToMany(mappedBy = "user")
    private List<Offer> offers;

    public User() {
    }

    public User(String login,
                String password,
                BigDecimal points,
                String contact,
                List<Offer> offers) {
        this.login = login;
        this.password = password;
        this.points = points;
        this.contact = contact;
        this.offers = offers;
    }
}
