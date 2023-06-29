package com.tw.session;


import com.tw.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class UserSession {        // 每個session對應一個使用者
    private User user;

    // Getter 和 Setter 方法

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
