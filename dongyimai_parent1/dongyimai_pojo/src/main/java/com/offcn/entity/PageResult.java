package com.offcn.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.entity
 * @date 2020/11/3 18:37
 * @Copyright © 2020-2021 中公教育
 */
public class PageResult implements Serializable {

    private List rows;//分页查询出来的记录
    private Long total;//总记录数

    public PageResult() {
    }

    public PageResult(Long total,List rows) {
        this.rows = rows;
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
