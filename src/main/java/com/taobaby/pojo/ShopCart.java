package com.taobaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author iuoly
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopCart {

  private String id;
  private String cartId;
  private String userId;
  private List<ShopCartProduct> shopCartProductList;

  public ShopCart(String id, String cartId, String userId) {
    this.id = id;
    this.cartId = cartId;
    this.userId = userId;
  }
}
