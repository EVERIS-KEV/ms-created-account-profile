package com.everis.createdaccountprofile.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class fromAccount {
	private String profile;
	@NotBlank(message = "Debe seleccionar un cliente.")
	private String idCustomer;

	public fromAccount(String idCustomer) {
		this.idCustomer = idCustomer; 
	}

}
