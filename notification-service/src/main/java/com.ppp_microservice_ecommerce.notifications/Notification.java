package com.ppp_microservice_ecommerce.notifications;


import com.ppp_microservice_ecommerce.clients.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notification extends BaseEntity {
    @Id
    @SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_seq")
    private Integer notificationId;

    private Integer originId;
    private String message;
}
