package com.taobaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopCartProduct {

  private String id;
  private String shopCartId;
  private String productId;
  private int productNum;
  private Product product;

  public ShopCartProduct(String id, String shopCartId, String productId, int productNum) {
    this.id = id;
    this.shopCartId = shopCartId;
    this.productId = productId;
    this.productNum = productNum;
  }
}
