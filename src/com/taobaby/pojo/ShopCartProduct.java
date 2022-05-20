package com.sample;


public class ShopCartProduct {

  private String id;
  private String shopCartId;
  private String productId;
  private long productNum;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getShopCartId() {
    return shopCartId;
  }

  public void setShopCartId(String shopCartId) {
    this.shopCartId = shopCartId;
  }


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }


  public long getProductNum() {
    return productNum;
  }

  public void setProductNum(long productNum) {
    this.productNum = productNum;
  }

}
