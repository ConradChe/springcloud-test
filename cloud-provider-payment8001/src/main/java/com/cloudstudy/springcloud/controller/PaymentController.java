package com.cloudstudy.springcloud.controller;

import com.cloudstudy.springcloud.entity.CommonResult;
import com.cloudstudy.springcloud.entity.Payment;
import com.cloudstudy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**********插入结果"+result);
        return result > 0 ? new CommonResult(200, "插入数据库成功", result) : new CommonResult(444, "插入数据库失败", null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果："+payment);
        return payment!=null? new CommonResult(200,"查询成功",payment): new CommonResult(444,"没有对应记录，查询ID："+id,null);
    }
}
