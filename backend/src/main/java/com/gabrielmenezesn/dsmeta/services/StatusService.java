package com.gabrielmenezesn.dsmeta.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusService {
  @GetMapping(path = "/status")
  public String check() {
    return "online";
  }
}
