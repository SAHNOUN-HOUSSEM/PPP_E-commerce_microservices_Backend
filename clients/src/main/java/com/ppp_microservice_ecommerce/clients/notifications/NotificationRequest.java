package com.ppp_microservice_ecommerce.clients.notifications;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest implements Serializable {
    @Serial
    private final static long serialVersionUID = 1;

    Integer orderID;
    //Integer ClientID;
    String message;
}
