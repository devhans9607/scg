package com.example.gw;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GWController {

    @PostMapping("/just2show")
    public String justGW() {
        return "This is My Gateway";
    }
}
