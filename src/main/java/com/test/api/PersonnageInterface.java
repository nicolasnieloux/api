package com.test.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface PersonnageInterface {
    @RequestMapping("/greeting")
    String greeting();
}
