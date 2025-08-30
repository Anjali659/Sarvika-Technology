package com.sarvika.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User" , url= "${service.user}")
public interface UserFeign {
    @GetMapping("/users/{id}")
    ResponseEntity<?> fetchUsersById(@PathVariable int id);

    @GetMapping("/users")
    ResponseEntity<?> fetchAllUser();

}
