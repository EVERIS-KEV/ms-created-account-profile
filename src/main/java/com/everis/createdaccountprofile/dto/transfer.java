package com.everis.createdaccountprofile.dto; 

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class transfer {
  @NotBlank(message = "Debe seleccionar un numero de cuenta.")
  private String accountEmisor; 
  @NotBlank(message = "Debe seleccionar un numero de cuenta.")
  private String accountRecep; 
  @Min(10)
  private double amount;  
  private String type = "Trasnferencia"; 
}
