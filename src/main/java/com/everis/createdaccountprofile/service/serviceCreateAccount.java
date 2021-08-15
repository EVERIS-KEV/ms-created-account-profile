package com.everis.createdaccountprofile.service;

import com.everis.createdaccountprofile.consumer.webClient;
import com.everis.createdaccountprofile.dto.fromAccount;
import com.everis.createdaccountprofile.dto.message;
import com.everis.createdaccountprofile.map.customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class serviceCreateAccount {

  private customer customerFind(String id) {
    return webClient.customer
      .get()
      .uri("/{id}", id)
      .retrieve()
      .bodyToMono(customer.class)
      .block();
  }

  private Boolean verifyCustomer(String id) {
    return webClient.customer
      .get()
      .uri("/verifyId/{id}", id)
      .retrieve()
      .bodyToMono(Boolean.class)
      .block();
  }

  private Boolean verifyCustomerCredits(String id) {
    return webClient.creditAccount
      .get()
      .uri("/verifyCustomer/{id}", id)
      .retrieve()
      .bodyToMono(Boolean.class)
      .block();
  }

  private Mono<Object> createProfileS(fromAccount model) {
    return webClient.savingAccount
      .post()
      .uri("/save")
      .body(Mono.just(model), fromAccount.class)
      .retrieve()
      .bodyToMono(Object.class);
  }

  private Mono<Object> createProfileP(fromAccount model) {
    return webClient.currentAccount
      .post()
      .uri("/save")
      .body(Mono.just(model), fromAccount.class)
      .retrieve()
      .bodyToMono(Object.class);
  }

  public Mono<Object> saveAccount(fromAccount model) { 
    String msg = "";

    if (verifyCustomer(model.getIdCustomer())) {
      if (verifyCustomerCredits(model.getIdCustomer())) {
        String type = customerFind(model.getIdCustomer()).getType();

        if (type.equals("personal")) {
          model.setPerfil("VIP");
          return createProfileS(model);
        } else {
          model.setPerfil("PYME");
          return createProfileP(model);
        }
      } else msg = "Necesita adquirir un credito en este banco.";
    } else msg = "Cliente no econtrado.";
    
    return Mono.just(new message(msg));
  }
}
