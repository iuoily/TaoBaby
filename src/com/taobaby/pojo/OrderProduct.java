package com.taobaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

  private String id;
  private String orderId;
  private String productId;
  private long productNum;

}
