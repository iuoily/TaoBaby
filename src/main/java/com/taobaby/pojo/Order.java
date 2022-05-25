package com.taobaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author iuoly
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

  private String id;
  private LocalDateTime createTime;
  private String receivingAddress;
  private String userId;
  private List<OrderProduct> orderProductList;

  public Order(String id, LocalDateTime createTime, String receivingAddress, String userId) {
    this.id = id;
    this.createTime = createTime;
    this.receivingAddress = receivingAddress;
    this.userId = userId;
  }

  public String getCreateTime() {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createTime);
  }

}
