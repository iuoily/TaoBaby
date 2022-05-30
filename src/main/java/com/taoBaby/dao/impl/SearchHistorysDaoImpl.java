package com.taoBaby.dao.impl;

import com.taoBaby.dao.SearchHistorysDao;
import com.taoBaby.pojo.SearchHistory;
import com.taoBaby.utils.DBUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author iuoily on 2022/5/19.
 */
public class SearchHistorysDaoImpl implements SearchHistorysDao {

    private Connection conn;

    public SearchHistorysDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<SearchHistory> selectAllHistory() throws Exception {
        return DBUtils.getBeanList(conn, SearchHistory.class, "select * from s_search_history order by num desc limit 10");
    }
}
