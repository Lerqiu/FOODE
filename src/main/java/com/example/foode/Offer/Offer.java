package com.example.foode.Offer;

import com.example.foode.Product.Product;
import com.example.foode.User.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offer")
@Data
public class Offer {

    @Id
    @SequenceGenerator(name = "offers_id_seq", sequenceName = "offers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "offers_id_seq")
    private Long id;

    @Column
    private BigDecimal price;

    @Column
    private LocalDate date;

    @Column
    private String location;

    @Column(length = 4000)
    private String description;

    @Column(length = 200)
    private String availability;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


}
