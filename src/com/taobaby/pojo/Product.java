package com.taobaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private String id;
  private String productName;
  private String productImage;
  private double price;
  private String productType;
  private String productDesc;
  private Timestamp createTime;
  private String productBrand;

}
