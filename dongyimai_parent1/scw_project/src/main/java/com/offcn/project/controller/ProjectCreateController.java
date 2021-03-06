package com.offcn.project.controller;


import com.alibaba.fastjson.JSON;
import com.offcn.dycommon.enums.ProjectStatusEnume;
import com.offcn.dycommon.response.AppResponse;
import com.offcn.project.contants.ProjectConstant;
import com.offcn.project.pojo.TReturn;
import com.offcn.project.service.ProjectCreateService;
import com.offcn.project.vo.req.ProjectBaseInfoVo;
import com.offcn.project.vo.req.ProjectRedisStorageVo;
import com.offcn.project.vo.req.ProjectReturnVo;
import com.offcn.vo.BaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "项目基本功能模块(创建，保存，项目基本信息获取，文件上传等)")
@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectCreateController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProjectCreateService projectCreateService;


    @ApiOperation("项目发起第一步，阅读同意协议")
    @PostMapping("/init")
    public AppResponse<String> init(BaseVo vo){
        String accessToken = vo.getAccessToken();
        //通过令牌获取用户id
        String memberId = stringRedisTemplate.opsForValue().get(accessToken);
        //用户id查出来为空
        if (StringUtils.isEmpty(memberId)){
            return AppResponse.fail("没有操作权限，请先登录");
        }

        int id = Integer.parseInt(memberId);
        //获取项目令牌，返回给用户
        String s = projectCreateService.initCreateProject(id);
        return AppResponse.ok(s);
    }

    @ApiOperation("项目发起第二步，保存项目的基本信息")
    @PostMapping("/savebaseInfo")
    public AppResponse<String> savebaseInfo(@RequestBody ProjectBaseInfoVo vo){
        //1.取得redis存储的项目令牌
        String projectToken = stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROFECT_PREFIX + vo.getProjectToken());
        //2.转换为redis存储对应的vo
        ProjectRedisStorageVo projectRedisStorageVo = JSON.parseObject(projectToken, ProjectRedisStorageVo.class);
        //3.将页面收集到的数据，复制到和redis对应的vo中
        BeanUtils.copyProperties(vo,projectRedisStorageVo);
        //4.将这个vo对象再转换为json字符串
        String jsonString = JSON.toJSONString(projectRedisStorageVo);
        //5.重新更新到redis
        stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROFECT_PREFIX+vo.getProjectToken(),jsonString);


        return AppResponse.ok("信息保存成功");
    }

    @ApiOperation("项目发起第三步，项目保存项目回报信息")
    @PostMapping("/savereturn")
    public AppResponse<Object> saveReturnInfo(@RequestBody List<ProjectReturnVo> pro){

        //1.先从redis中取出对象字符串
        String projectToken = pro.get(0).getProjectToken();
        String jsonStr = stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROFECT_PREFIX + projectToken);
        ProjectRedisStorageVo projectRedisStorageVo = JSON.parseObject(jsonStr, ProjectRedisStorageVo.class);
        //2.将页面收回来的数据重新装回redis
        List<TReturn> returns = new ArrayList<>();

        for (ProjectReturnVo projectReturnVo : pro){

            TReturn tReturn = new TReturn();
            BeanUtils.copyProperties(projectReturnVo,tReturn);
            returns.add(tReturn);
        }

        //3.更新return集合
        projectRedisStorageVo.setProjectReturns(returns);
        String jsonString = JSON.toJSONString(projectRedisStorageVo);
        //4.更新redis
        stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROFECT_PREFIX+projectToken,jsonString);
        return AppResponse.ok("项目回报信息保存完毕");

    }


    @ApiOperation("项目发起第4步-项目保存项目回报信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessToken",value = "用户令牌",required = true),
            @ApiImplicitParam(name = "projectToken",value="项目标识",required = true),
            @ApiImplicitParam(name="ops",value="用户操作类型 0-保存草稿 1-提交审核",required = true)})
    @PostMapping("/submit")
    public AppResponse<Object> submit(String accessToken,String projectToken,String ops){

        //1检查用户是否登录
        String memberId = stringRedisTemplate.opsForValue().get(accessToken);
        if (StringUtils.isEmpty(memberId)){
            return AppResponse.fail("没有操作权限，请先登录");
        }

        //2.根据项目token，获取项目存储在redis中的信息
        String projectJson = stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROFECT_PREFIX + projectToken);
        ProjectRedisStorageVo projectRedisStorageVo = JSON.parseObject(projectJson, ProjectRedisStorageVo.class);
        //3.判断用户操作类型不为空，进行处理
        if (!StringUtils.isEmpty(ops)){
            //提交审核，则进行项目的保存操作
            if (ops.equals("1")){
                //获取项目状态，提交枚举
                ProjectStatusEnume submitAuth = ProjectStatusEnume.SUBMIT_AUTH;
                projectCreateService.saveProjectInfo(submitAuth,projectRedisStorageVo);
                return AppResponse.ok(null);
            }else if (ops.equals("0")){
                //获取项目 草稿状态
                ProjectStatusEnume projectStatusEnume = ProjectStatusEnume.DRAFT;
                //保存草稿
                projectCreateService.saveProjectInfo(projectStatusEnume,projectRedisStorageVo);
                return AppResponse.ok(null);

            }else{
                AppResponse<Object> appResponse = AppResponse.fail(null);
                appResponse.setMsg("不支持此操作");
                return appResponse;

            }
        }
        return AppResponse.fail(null);
    }


}
