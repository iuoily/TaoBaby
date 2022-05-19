package com.taobaby.dao.impl;

import com.taobaby.dao.SearchHistorysDao;
import com.taobaby.pojo.SearchHistory;
import com.taobaby.utils.JdbcUtils;

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
        return JdbcUtils.getBeanList(conn, SearchHistory.class, "select * from s_search_history order by num desc limit 10");
    }
}
