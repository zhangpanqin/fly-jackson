package com.mflyyou;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public User demo(User user) {
        return user;
    }

    @GetMapping("/demo2")
    public User2 demo2(User2 user) {
        return user;
    }
}
