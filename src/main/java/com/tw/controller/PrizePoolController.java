package com.tw.controller;


import com.tw.model.PrizePool;
import com.tw.model.User;
import com.tw.repository.PrizePoolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PrizePoolController {

    private PrizePoolRepository prizePoolRepository;

    public PrizePoolController(PrizePoolRepository prizePoolRepository) {
        this.prizePoolRepository = prizePoolRepository;
    }

    // 取得獎池資料
    @GetMapping("/prizePools")
    public PrizePool getPrizePool(){
        return prizePoolRepository.findById(1).orElse(null);
    }


    // 更新獎池資料
    @PutMapping("/prizePools")
    public Boolean updatePrizePool(@RequestParam int lumpSum){
        PrizePool prizePool = prizePoolRepository.findById(1).orElse(null);
        prizePool.setLumpSum(lumpSum);
        prizePoolRepository.save(prizePool);
        return true;
    }
}
