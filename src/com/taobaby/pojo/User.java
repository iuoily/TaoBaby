package com.taobaby.pojo;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class User {
  private String id;
  private String username;
  private String password;
  private Integer type;
}
