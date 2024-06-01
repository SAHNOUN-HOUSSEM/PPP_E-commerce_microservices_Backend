package com.ppp_microservice_ecommerce.orders.entities;


import com.ppp_microservice_ecommerce.clients.common.BaseEntity;
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
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;
    @OneToMany(mappedBy ="order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemsList;
    private Integer userId;
}