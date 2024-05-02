package com.ppp_microservice_ecommerce.products.entites;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_id_sequence",
            sequenceName = "category_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "category_id_sequence"
    )
    private Integer id;
    String description;
    String image;
    String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
