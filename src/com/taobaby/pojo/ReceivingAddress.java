package com.sample;


public class ReceivingAddress {

  private String id;
  private String receivingAddress;
  private String receivingPerson;
  private long mobilePhone;
  private String userId;
  private long isDefault;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getReceivingAddress() {
    return receivingAddress;
  }

  public void setReceivingAddress(String receivingAddress) {
    this.receivingAddress = receivingAddress;
  }


  public String getReceivingPerson() {
    return receivingPerson;
  }

  public void setReceivingPerson(String receivingPerson) {
    this.receivingPerson = receivingPerson;
  }


  public long getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(long mobilePhone) {
    this.mobilePhone = mobilePhone;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public long getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(long isDefault) {
    this.isDefault = isDefault;
  }

}
