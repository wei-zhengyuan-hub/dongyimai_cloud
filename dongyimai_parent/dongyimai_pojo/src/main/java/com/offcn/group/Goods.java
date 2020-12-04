package com.offcn.group;

import com.offcn.pojo.TbGoods;
import com.offcn.pojo.TbGoodsDesc;
import com.offcn.pojo.TbItem;

import java.io.Serializable;
import java.util.List;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.group
 * @date 2020/11/6 20:40
 * @Copyright © 2020-2021 中公教育
 *商品信息录入综合表
 */
public class Goods implements Serializable {

    private TbGoods goods;//商品表
    private TbGoodsDesc goodsDesc;//商品扩展表
    private List<TbItem> itemList;//商品SKU

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }
}
