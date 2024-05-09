package com.ppp_microservice_ecommerce.orders.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order{
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String orderNumber;
    @OneToMany(mappedBy ="order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemsList;
}