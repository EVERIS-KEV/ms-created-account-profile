package com.everis.createdaccountprofile.consumer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class webClient {
  private static String gateway = "host.docker.internal:8090";

  public static WebClient customer = WebClient.create(
    "http://" + gateway + "/service/customers"
  );

  public static WebClient creditAccount = WebClient.create(
    "http://" + gateway + "/service/credits"
  );

  public static WebClient currentAccount = WebClient
    .builder()
    .baseUrl("http://" + gateway + "/service/currentAccount")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .build();

  public static WebClient savingAccount = WebClient
    .builder()
    .baseUrl("http://" + gateway + "/service/savingAccount")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .build();

  public static WebClient fixedAccount = WebClient
    .builder()
    .baseUrl("http://" + gateway + "/service/fixedTermAccount")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .build();
}
