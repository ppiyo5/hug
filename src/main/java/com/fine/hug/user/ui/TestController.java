package com.fine.hug.user.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/token")
    public String token() {
        return "<h1>home</h1>";
    }
}
