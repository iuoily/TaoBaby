package com.taoBaby.service;

import com.taoBaby.pojo.SearchHistory;

import java.util.List;

/**
 * @author iuoily on 2022/5/19.
 */
public interface SearchHistorysService {
    /**
     * 搜索历史
     * @return
     */
    List<SearchHistory> getAllHistory() throws Exception;
}
