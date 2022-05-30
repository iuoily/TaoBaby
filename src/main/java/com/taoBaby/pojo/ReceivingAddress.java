package com.taoBaby.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceivingAddress {

  private String id;
  private String receivingAddress;
  private String receivingPerson;
  private long mobilePhone;
  private String userId;
  private int isDefault;

}
