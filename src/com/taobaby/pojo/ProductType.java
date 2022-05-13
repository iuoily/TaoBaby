package com.taobaby.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author iuoily on 2022/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {
  private String id;
  private String productTypeName;
  private String productTypeDesc;
  private String productTypeIcon;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductType that = (ProductType) o;
    return id.equals(that.id) ||
            productTypeName.equals(that.productTypeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productTypeName);
  }
}
