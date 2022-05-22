package com.taobaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

  private String id;
  private String orderId;
  private String productId;
  private int productNum;
  private Product product;

  public OrderProduct(String id, String orderId, String productId, int productNum) {
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
    this.productNum = productNum;
  }
}
