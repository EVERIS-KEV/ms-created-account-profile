package com.everis.createdaccountprofile.map;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class customer {
  @Id
  private String idclient;

  private String dni;
  private String firstname;
  private String lastname;
  private String type;
}
