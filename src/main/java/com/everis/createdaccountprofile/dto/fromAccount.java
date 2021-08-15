package com.everis.createdaccountprofile.dto;

import com.everis.createdaccountprofile.consumer.webClient;
import com.everis.createdaccountprofile.map.customer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class fromAccount {
  private String perfil;
  private String idCustomer;

  public fromAccount(String idCustomer) {
    this.idCustomer = idCustomer; 
  }
  
}
