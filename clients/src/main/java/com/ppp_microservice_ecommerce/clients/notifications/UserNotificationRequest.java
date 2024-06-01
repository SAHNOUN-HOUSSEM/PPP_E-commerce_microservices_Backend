package com.ppp_microservice_ecommerce.clients.notifications;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationRequest implements Serializable {
    @Serial
    private final static long serialVersionUID = 1;

    Integer userID;
    String message;


}
