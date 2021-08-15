package com.everis.createdaccountprofile.controller;

import com.everis.createdaccountprofile.dto.fromAccount;
import com.everis.createdaccountprofile.service.serviceCreateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(
  origins = "*",
  methods = {
    RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
  }
)
@RequestMapping
public class controllerCreateAccount {
  @Autowired
  serviceCreateAccount service;

  @GetMapping("/")
  public Mono<String> get() {
    return Mono.just("");
  }

  @PostMapping("/save")
  public Mono<Object> create(@RequestBody fromAccount model) {
    return service.saveAccount(model);
  }
}
