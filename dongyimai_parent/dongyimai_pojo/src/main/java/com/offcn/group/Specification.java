package com.offcn.group;

import com.offcn.pojo.TbSpecification;
import com.offcn.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.group
 * @date 2020/11/4 19:54
 * @Copyright © 2020-2021 中公教育
 */
public class Specification implements Serializable {

    private TbSpecification specification; //规格名称
    private List<TbSpecificationOption> specificationOptionList; //规格选项集合

    public Specification() {
    }

    public Specification(TbSpecification specification, List<TbSpecificationOption> specificationOptionList) {
        specification = specification;
        this.specificationOptionList = specificationOptionList;
    }

    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
