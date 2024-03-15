package com.github.vishalm1029.HRMS;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/content")
//@CrossOrigin(origins = "http://localhost:4200")
class ContentController {

    @GetMapping("/public")
    public String publicAccess() {
        return "Public content.";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "test Working";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {


        return "Now displaying restricted user content.";
    }
}
