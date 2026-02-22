package com.spring.backend.Controller.Private;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiv1/private")
public class demo {

    @GetMapping("/bye")
    public ResponseEntity<?> bye(){
        System.out.println("hello-1-1");
        return ResponseEntity.ok().body("hello");
    }
}
