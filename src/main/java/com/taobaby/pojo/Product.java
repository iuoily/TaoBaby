package com.taobaby.pojo;

import com.taobaby.utils.DBUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author admin
 */
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
  private LocalDateTime createTime;
  private String productBrand;
  private int sales;

  public Product(String id, String productName, String productImage, double price, String productType, String productDesc, LocalDateTime createTime, String productBrand) {
    this.id = id;
    this.productName = productName;
    this.productImage = productImage;
    this.price = price;
    this.productType = productType;
    this.productDesc = productDesc;
    this.createTime = createTime;
    this.productBrand = productBrand;
  }

  public String getCreateTime() {
    return DBUtils.format(createTime);
  }

}
