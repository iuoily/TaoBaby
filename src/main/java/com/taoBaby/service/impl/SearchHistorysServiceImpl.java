package com.taoBaby.service.impl;

import com.taoBaby.dao.SearchHistorysDao;
import com.taoBaby.dao.impl.SearchHistorysDaoImpl;
import com.taoBaby.pojo.SearchHistory;
import com.taoBaby.service.SearchHistorysService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

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
        searchHistorysDao = SpringUtils.getBean(SearchHistorysDao.class);
        List<SearchHistory> searchHistories = searchHistorysDao.selectAllHistory();
        DBUtils.close(conn);
        return searchHistories;
    }
}
