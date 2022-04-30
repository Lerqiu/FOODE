package com.example.foode.User;

import com.example.foode.Fridge.Fridge;
import com.example.foode.Offer.Offer;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "user")
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

    @Column
    private BigDecimal points;

    @Column
    private String contact;

    @OneToOne
    @JoinColumn(name = "fridge_id", referencedColumnName = "id")
    private Fridge fridge;


    @OneToMany(mappedBy = "user")
    private List<Offer> offers;


}
