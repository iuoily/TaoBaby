package com.taoBaby.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 泛型 page
 * @param <T>
 * @author iuoily on 2022/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页展示的条数
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Integer pageNumTotal;

    /**
     * 分页数据
     */
    private List<T> pageData;

    /**
     * 获取总页数
     * @return 总页数
     */
    public Double getPageTotal() {
        return Math.ceil(pageNumTotal * 1.0 / pageSize);
    }
}
