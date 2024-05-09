package com.ppp_microservice_ecommerce.products.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Brand {
    @Id
    @SequenceGenerator(
            name = "brand_id_sequence",
            sequenceName = "brand_id_sequence"
    )
    @GeneratedValue(
            generator = "brand_id_sequence"
    )
    private Integer id;
    private String image;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

}
