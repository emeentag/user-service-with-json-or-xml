package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * AppController
 */
@Controller
public class AppController {

  public ResponseEntity<String> getIndex() {
    return ResponseEntity.ok("Please use /member enpoint for application.");
  }
}