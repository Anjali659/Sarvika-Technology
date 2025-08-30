package com.sarvika.controller;

import com.sarvika.dto.OrderRequestDTO;
import com.sarvika.service.Orderservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class Ordercontroller {
    private final Orderservice orderservice;

    public Ordercontroller(Orderservice orderservice){
        this.orderservice= orderservice;
    }
    @PostMapping()
    public ResponseEntity createOrder(@RequestBody OrderRequestDTO order){
        return  ResponseEntity.ok(orderservice.saveOrder(order));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateOrder( @PathVariable int id,@RequestBody OrderRequestDTO users){
        return  ResponseEntity.ok(orderservice.updateOrder(id,users));
    }
    @GetMapping("/{id}")
    public ResponseEntity getOrder(@PathVariable int id){
        return  ResponseEntity.ok(orderservice.fetchOrder(id));
    }
    @GetMapping()
    public  ResponseEntity getAllUser(){
        return  ResponseEntity.ok(orderservice.fetchAllOrder());
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity deleteUSers(@PathVariable int id){
        return  ResponseEntity.ok(orderservice.deleteOrder(id));
    }


}
