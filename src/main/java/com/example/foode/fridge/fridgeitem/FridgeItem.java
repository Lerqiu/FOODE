package com.example.foode.fridge.fridgeitem;

import com.example.foode.fridge.Fridge;
import com.example.foode.product.persistence.ProductEntity;
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
    private ProductEntity productEntity;

    @Column(nullable = false)
    private Long quantity;

}
