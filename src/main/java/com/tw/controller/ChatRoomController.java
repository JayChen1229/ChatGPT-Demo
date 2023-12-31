package com.tw.controller;


import com.tw.model.User;
import com.tw.service.ChatRoomService;
import com.tw.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ChatRoomController {

    private ChatRoomService chatRoomService;
    private UserService userService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService,UserService userService) {
        this.chatRoomService = chatRoomService;
        this.userService = userService;
    }

    @PostMapping("/content")
    public Boolean login(@RequestParam String content, HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setLevel(user.getLevel()-2);
        userService.save(user);
        chatRoomService.readResponse(content);
        System.out.println(content);
        return true;
    }

    // 這段程式碼是一個使用Spring WebFlux框架實現的Server-Sent Events（SSE）端點。SSE是一種基於HTTP的輕量級通訊協議，它允許服務器向客戶端推送持續的資料流。
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse() {
        return chatRoomService.getOutput();
    }
}
