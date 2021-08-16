package com.everis.createdaccountprofile.controller;

import com.everis.createdaccountprofile.dto.*;
import com.everis.createdaccountprofile.service.serviceCreateAccount;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

  @PostMapping("/transferAccount")
  public Mono<Object> transferAccount(
    @RequestBody @Valid transfer model,
    BindingResult bindinResult
  ) {
    String msg = "";

    if (bindinResult.hasErrors()) {
      for (int i = 0; i < bindinResult.getAllErrors().size(); i++) msg =
        bindinResult.getAllErrors().get(0).getDefaultMessage();
      return Mono.just(new message(msg));
    }

    return service.transferAccount(model);
  }
}
