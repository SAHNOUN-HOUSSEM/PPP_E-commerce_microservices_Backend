package com.ppp_microservice_ecommece.sse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {
    @GetMapping("/sse/{userID}")
    public SseEmitter handleSse(@PathVariable String userID) {
        SseEmitter emitter = new SseEmitter();
        SseService.addEmitter(userID, emitter);
        return emitter;
    }

}
