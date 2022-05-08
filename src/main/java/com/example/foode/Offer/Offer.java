package com.example.foode.Offer;

import com.example.foode.City.City;
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

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @Column(length = 4000, nullable = false)
    private String description;

    @Column(length = 200, nullable = false)
    private String availability;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public void setFrom(Offer offer) {
        this.setAvailability(offer.getAvailability());
        this.setCity(offer.getCity());
        this.setDate(offer.getDate());
        this.setDescription(offer.getDescription());
        this.setPrice(offer.getPrice());
        this.setProduct(offer.getProduct());
        this.setCity(offer.getCity());
    }

}
