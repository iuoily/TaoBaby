package com.taoBaby.dao;

import com.taoBaby.pojo.SearchHistory;

import java.util.List;

/**
 * @author iuoily on 2022/5/19.
 */
public interface SearchHistorysDao {

    /**
     * 搜索历史
     * @return
     */
    List<SearchHistory> selectAllHistory() throws Exception;
}
