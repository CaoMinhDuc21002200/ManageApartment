package com.cmd.manageapartment.manageapartment.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    //Just for testing
    @GetMapping("dashboard")
    public ResponseEntity<String> getAdminDashboard() {
        return ResponseEntity.ok("Welcome to the Admin Dashboard");
    }
    @GetMapping("test")
    public ResponseEntity<String> testAdminAccess() {
        return ResponseEntity.ok("Admin access successful");
    }

}