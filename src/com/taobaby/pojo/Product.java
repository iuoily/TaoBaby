package com.taobaby.pojo;

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

  public String getCreateTime() {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createTime);
  }

}
