package com.example.foode.user;

import com.example.foode.fridge.Fridge;
import com.example.foode.offer.persistence.OfferEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "user_detail")
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

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<OfferEntity> offers;

    public User() {
    }

    public User(String login,
                String password,
                BigDecimal points,
                String contact,
                List<OfferEntity> offers) {
        this.login = login;
        this.password = password;
        this.points = points;
        this.contact = contact;
        this.offers = offers;
    }
}
