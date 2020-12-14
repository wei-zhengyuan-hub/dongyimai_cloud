package com.offcn.controller;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;
import com.offcn.service.BrandService;
import com.offcn.vo.req.SearchPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description BrandController
 * @since 2020/12/5 16:50
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "品牌列表操作")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation("查询全部品牌信息")
    @GetMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    @ApiOperation("新增品牌")
    @PostMapping("/add")
    @ApiImplicitParam(name = "brand", value = "品牌对象")
    public String add(@RequestBody TbBrand brand) {
        brandService.add(brand);
        return "ok";
    }

    @ApiOperation("用id查询品牌")
    @GetMapping("/findOne/{brandId}")
    @ApiImplicitParam(name = "brandId", value = "品牌id")
    public TbBrand findOne(@PathVariable("brandId") Long brandId) {
        return brandService.findOne(brandId);
    }

    @ApiOperation("修改品牌")
    @PutMapping("/update")
    @ApiImplicitParam(name = "brand", value = "品牌对象")
    public String update(TbBrand brand) {
        brandService.update(brand);
        return "ok";
    }

    @ApiOperation("删除品牌")
    @DeleteMapping("/delete")
    @ApiImplicitParam(name = "brandIds", value = "品牌id集合")
    public String delete(Long[] brandIds) {
        brandService.delete(brandIds);
        return "ok";
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping("/search")
    @ApiImplicitParam(name = "searchPage",value = "查询条件对象，包含品牌对象，当前页码，每页显示记录数")
    public PageResult search(@RequestBody SearchPage searchPage) {
        return brandService.findPage(searchPage.getBrand(),searchPage.getPage(),searchPage.getRows());
    }
}
