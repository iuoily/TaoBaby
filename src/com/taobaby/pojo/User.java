package com.taobaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String id;
  private String username;
  private String password;
  private Integer type;
}
