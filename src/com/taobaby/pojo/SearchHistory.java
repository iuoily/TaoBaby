package com.taobaby.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistory {

  private String id;
  private String searchWords;
  private int num;
  private LocalDateTime searchTime;


  public LocalDateTime getSearchTime() {
    return searchTime;
  }

}
