package com.everis.createdaccountprofile.service;

import org.springframework.stereotype.Service;

import com.everis.createdaccountprofile.consumer.webClient;
import com.everis.createdaccountprofile.dto.fromAccount;
import com.everis.createdaccountprofile.dto.message;
import com.everis.createdaccountprofile.dto.transfer;
import com.everis.createdaccountprofile.map.customer;

import reactor.core.publisher.Mono;

@Service
public class serviceCreateAccount {

	private customer customerFind(String id) {
		return webClient.customer.get().uri("/{id}", id).retrieve().bodyToMono(customer.class).block();
	}

	private Boolean verifyCustomer(String id) {
		return webClient.customer.get().uri("/verifyId/{id}", id).retrieve().bodyToMono(Boolean.class).block();
	}

	private Boolean verifyCustomerCredits(String id) {
		return webClient.creditAccount.get().uri("/verifyCustomer/{id}", id).retrieve().bodyToMono(Boolean.class)
				.block();
	}

	private Boolean verifyNumberCC(String number) {
		return webClient.currentAccount.get().uri("/verifyByNumberAccount/" + number).retrieve()
				.bodyToMono(Boolean.class).block();
	}

	private Boolean verifyNumberSC(String number) {
		return webClient.savingAccount.get().uri("/verifyByNumberAccount/" + number).retrieve()
				.bodyToMono(Boolean.class).block();
	}

	private Boolean verifyNumberFC(String number) {
		return webClient.fixedAccount.get().uri("/verifyByNumberAccount/" + number).retrieve().bodyToMono(Boolean.class)
				.block();
	}

	private Mono<Object> createProfileS(fromAccount model) {
		return webClient.savingAccount.post().uri("/save").body(Mono.just(model), fromAccount.class).retrieve()
				.bodyToMono(Object.class);
	}

	private Mono<Object> createProfileP(fromAccount model) {
		return webClient.currentAccount.post().uri("/save").body(Mono.just(model), fromAccount.class).retrieve()
				.bodyToMono(Object.class);
	}

	private Boolean verifyCE(String number) {
		if (verifyNumberCC(number) || verifyNumberSC(number) || verifyNumberFC(number)) {
			return true;
		}
		return false;
	}

	private Boolean verifyCR(String number) {
		if (verifyNumberCC(number) || verifyNumberSC(number) || verifyNumberFC(number)) {
			return true;
		}
		return false;
	}

	private Mono<Object> addTransfer(String number, transfer model) {
		if (verifyNumberCC(number)) {
			return Mono.just(webClient.currentAccount.post().uri("/movememts").body(Mono.just(model), transfer.class)
					.retrieve().bodyToMono(Object.class).block());
		}
		if (verifyNumberSC(number)) {
			return Mono.just(webClient.savingAccount.post().uri("/movememts").body(Mono.just(model), transfer.class)
					.retrieve().bodyToMono(Object.class).block());
		}
		if (verifyNumberFC(number)) {
			return Mono.just(webClient.fixedAccount.post().uri("/movememts").body(Mono.just(model), transfer.class)
					.retrieve().bodyToMono(Object.class).block());
		} else {
			return Mono.just(new message("Numero emisor incorrecto."));
		}
	}

	public Mono<Object> transferAccount(transfer model) {
		if (!verifyCR(model.getAccountRecep())) {
			return Mono.just(new message("Las cuenta receptora no existe."));
		}

		if (model.getAccountEmisor().equals(model.getAccountRecep())) {
			return Mono.just(new message("Las cuentas no pueden ser iguales."));
		}

		return addTransfer(model.getAccountEmisor(), model);
	}

	public Mono<Object> saveAccount(fromAccount model) {
		String msg = "";

		if (verifyCustomer(model.getIdCustomer())) {
			if (verifyCustomerCredits(model.getIdCustomer())) {
				String type = customerFind(model.getIdCustomer()).getType();

				if (type.equals("personal")) {
					model.setProfile("VIP");
					return createProfileS(model);
				} else {
					model.setProfile("PYME");
					return createProfileP(model);
				}
			} else {
				msg = "Necesita adquirir un credito en este banco.";
			}
		} else {
			msg = "Cliente no econtrado.";
		}

		return Mono.just(new message(msg));
	}
}
