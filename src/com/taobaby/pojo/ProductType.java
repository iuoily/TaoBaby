package com.taobaby.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

/**
 * @author iuoily on 2022/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductType {
  private String id;
  private String productTypeName;
  private String productTypeDesc;
  private String productTypeIcon;
}