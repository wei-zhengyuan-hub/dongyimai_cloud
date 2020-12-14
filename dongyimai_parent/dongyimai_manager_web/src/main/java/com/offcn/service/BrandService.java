package com.offcn.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;

/**
 * 品牌服务层接口
 */
public interface BrandService {

    /**
     * 查询所有品牌
     *
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 添加品牌
     *
     * @param brand
     */
    public void add(TbBrand brand);

    /**
     * 查询品牌
     *
     * @param brandId 品牌id
     * @return
     */
    public TbBrand findOne(Long brandId);

    /**
     * 修改品牌信息
     *
     * @param brand
     */
    public void update(TbBrand brand);

    /**
     * 删除品牌
     *
     * @param brandIds 品牌Id
     */
    public void delete(Long[] brandIds);

    /**
     * 分页查询
     * @param brand
     * @param pageNum  当前页
     * @param pageSize 每页显示记录数
     * @return
     */
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize);

}
