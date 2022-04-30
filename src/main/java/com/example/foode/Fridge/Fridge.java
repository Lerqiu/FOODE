package com.example.foode.Fridge;

import com.example.foode.Fridge.FridgeItem.FridgeItem;
import com.example.foode.User.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fridge")
@Data
public class Fridge {

    @Id
    @SequenceGenerator(name = "fridge_id_seq", sequenceName = "fridge_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fridge_id_seq")
    private Long id;

    @OneToMany(mappedBy = "fridge")
    private List<FridgeItem> fridgeItems;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
