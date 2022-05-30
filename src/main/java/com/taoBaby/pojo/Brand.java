package com.taoBaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

  private String id;
  private String brandName;
  private String brandType;
  private String brandImg;

}
