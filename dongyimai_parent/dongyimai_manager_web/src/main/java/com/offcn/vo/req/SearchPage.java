package com.offcn.vo.req;

import com.offcn.pojo.TbBrand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wzy
 * @version 0.0.3
 * @description SearchPage
 * @since 2020/12/5 21:50
 */
@ApiModel("分页查询实体类")
public class SearchPage implements Serializable {

    @ApiModelProperty("品牌对象")
    private TbBrand brand;
    @ApiModelProperty("当前页")
    private int page;
    @ApiModelProperty("每页显示记录数")
    private int rows;

    public TbBrand getBrand() {
        return brand;
    }

    public void setBrand(TbBrand brand) {
        this.brand = brand;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
