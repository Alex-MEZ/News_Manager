package com.newsmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecuredController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secured-endpoint")
    public ResponseEntity<String> getSecuredData() {
        return ResponseEntity.ok("Secured data for ADMIN");
    }
}
