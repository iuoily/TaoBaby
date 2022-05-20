package com.sample;


public class OrderProduct {

  private String id;
  private String orderId;
  private String productId;
  private long productNum;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
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
