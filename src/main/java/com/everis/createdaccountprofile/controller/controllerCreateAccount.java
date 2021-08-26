package com.everis.createdaccountprofile.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everis.createdaccountprofile.dto.fromAccount;
import com.everis.createdaccountprofile.dto.message;
import com.everis.createdaccountprofile.dto.transfer;
import com.everis.createdaccountprofile.service.serviceCreateAccount;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping
public class controllerCreateAccount {
	@Autowired
	serviceCreateAccount service;

	private Mono<Object> BindingResultErrors(BindingResult bindinResult){
		String msg = "";

		for (int i = 0; i < bindinResult.getAllErrors().size(); i++) {
			msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
		} 
		return Mono.just(new message(msg));
	}

	@GetMapping("/")
	public Mono<String> get() {
		return Mono.just("");
	}

	@PostMapping("/save")
	public Mono<Object> create(@RequestBody @Valid fromAccount model, BindingResult bindinResult) {
		if (bindinResult.hasErrors()) return BindingResultErrors(bindinResult);

		return service.saveAccount(model);
	}

	@PostMapping("/transferAccount")
	public Mono<Object> transferAccount(@RequestBody @Valid transfer model, BindingResult bindinResult) {
		if (bindinResult.hasErrors()) return BindingResultErrors(bindinResult);

		return service.transferAccount(model);
	}
}
