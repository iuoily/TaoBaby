package com.taobaby.service.impl;

import com.taobaby.dao.SearchHistorysDao;
import com.taobaby.dao.impl.SearchHistorysDaoImpl;
import com.taobaby.pojo.SearchHistory;
import com.taobaby.service.SearchHistorysService;
import com.taobaby.utils.DBUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author iuoily on 2022/5/19.
 */
public class SearchHistorysServiceImpl implements SearchHistorysService {

    private SearchHistorysDao searchHistorysDao = null;

    @Override
    public List<SearchHistory> getAllHistory() throws Exception {
        Connection conn = DBUtils.getConn();
        searchHistorysDao = new SearchHistorysDaoImpl(conn);
        List<SearchHistory> searchHistories = searchHistorysDao.selectAllHistory();
        DBUtils.close(conn);
        return searchHistories;
    }
}
