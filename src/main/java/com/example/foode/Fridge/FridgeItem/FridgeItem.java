package com.example.foode.Fridge.FridgeItem;

import com.example.foode.Fridge.Fridge;
import com.example.foode.Product.Product;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "fridge_item")
@Data
public class FridgeItem {

    @Id
    @SequenceGenerator(name = "fridge_item_id_seq", sequenceName = "fridge_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fridge_item_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fridge_id", referencedColumnName = "id")
    private Fridge fridge;

    @OneToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private Long quantity;

}
