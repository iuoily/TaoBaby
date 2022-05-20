package com.taobaby.pojo;


import java.time.LocalDateTime;

public class SearchHistory {

  private String id;
  private String searchWords;
  private int num;
  private LocalDateTime searchTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getSearchWords() {
    return searchWords;
  }

  public void setSearchWords(String searchWords) {
    this.searchWords = searchWords;
  }


  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }


  public LocalDateTime getSearchTime() {
    return searchTime;
  }

  public void setSearchTime(LocalDateTime searchTime) {
    this.searchTime = searchTime;
  }

}
