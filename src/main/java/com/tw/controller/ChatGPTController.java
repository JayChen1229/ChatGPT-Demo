package com.tw.controller;


import com.tw.model.User;
import com.tw.service.ChatGPTService;
import com.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class ChatGPTController {

    private ChatGPTService chatGPTService;

    @Autowired
    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping("/content")
    public Boolean login(@RequestParam String content){
        chatGPTService.readResponse(content);
        System.out.println(content);
        return true;
    }


    // 這段程式碼是一個使用Spring WebFlux框架實現的Server-Sent Events（SSE）端點。SSE是一種基於HTTP的輕量級通訊協議，它允許服務器向客戶端推送持續的資料流。
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse() {
        return chatGPTService.getOutput();
    }
}
