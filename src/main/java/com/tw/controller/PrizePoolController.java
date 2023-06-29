package com.tw.controller;


import com.tw.model.PrizePool;
import com.tw.model.User;
import com.tw.repository.PrizePoolRepository;
import com.tw.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PrizePoolController {

    private PrizePoolRepository prizePoolRepository;
    private UserService userService;

    public PrizePoolController(PrizePoolRepository prizePoolRepository,UserService userService) {
        this.prizePoolRepository = prizePoolRepository;
        this.userService = userService;
    }

    // 取得獎池資料
    @GetMapping("/prizePools")
    public double getPrizePool(){
        PrizePool prizePool = prizePoolRepository.findById(1).orElse(null);
        return prizePool.getLumpSum();
    }

    // 更新獎池資料
    @GetMapping("/prizePools/update")
    public double getPrizePoolUpdate() {
        PrizePool prizePool = prizePoolRepository.findById(1).orElse(null);
        if (prizePool != null) {
            prizePool.setLumpSum(prizePool.getLumpSum() + 100);
            prizePoolRepository.save(prizePool); // 保存更新後的獎池數據
            return prizePool.getLumpSum();
        }
        return 0.0; // 或者返回其他預設值，表示未找到獎池數據
    }

    // 將獎池歸0
    @GetMapping("/prizePools/zero")
    public double updatePrizePoolToZero(HttpSession session){
        PrizePool prizePool = prizePoolRepository.findById(1).orElse(null);
        User user = (User) session.getAttribute("user");
        user.setMoney((int) (user.getMoney()+prizePool.getLumpSum()));
        userService.save(user);
        prizePool.setLumpSum(0);
        prizePoolRepository.save(prizePool);
        return prizePool.getLumpSum();
    }
}
