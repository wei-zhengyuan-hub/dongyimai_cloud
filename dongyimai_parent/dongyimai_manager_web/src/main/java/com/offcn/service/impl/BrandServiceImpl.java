package com.offcn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.pojo.TbBrand;
import com.offcn.pojo.TbBrandExample;
import com.offcn.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description BrandServiceImpl
 * @since 2020/12/5 16:29
 * 品牌服务实现层
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private TbBrandMapper brandMapper;

    /**
     * 查询所有品牌
     *
     * @return
     */
    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }

    /**
     * 添加品牌
     *
     * @param brand
     */
    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    /**
     * 查询品牌
     *
     * @param brandId 品牌id
     * @return
     */
    @Override
    public TbBrand findOne(Long brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    /**
     * 品牌修改
     *
     * @param brand
     */
    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 删除品牌
     *
     * @param brandIds 品牌Id
     */
    @Override
    public void delete(Long[] brandIds) {
        for (Long brandId : brandIds) {
            brandMapper.deleteByPrimaryKey(brandId);
        }

    }

    /**
     * 分页查询
     * @param brand
     * @param pageNum  当前页
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        TbBrandExample tbBrandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
        if (brand!=null) {
            if (brand.getName()!=null){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if (brand.getFirstChar()!=null){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(tbBrandExample);

        return new PageResult(page.getTotal(),page.getResult());
    }


}
