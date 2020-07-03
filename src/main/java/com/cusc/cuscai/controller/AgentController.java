package com.cusc.cuscai.controller;

import com.cusc.cuscai.entity.apibo.AgentBO;
import com.cusc.cuscai.service.AgentService;
import com.cusc.cuscai.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author lxy
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/6/21
 * @discription null
 * @usage null
 */
@RestController
@RequestMapping("/agent")
@Api(tags = "Agent相关接口")
class AgentController {

    @Autowired
    AgentService agentService;

    @PostMapping("/search")
    @ResponseBody
    @ApiOperation("查询Agent")
    public Result search(
            @RequestParam("adminID") @ApiParam(value = "管理员用户id", required = true) int adminID,
            @RequestParam("agentID") @ApiParam(value = "要查看的Agent id", required = true) int agentID
    ) {

        AgentBO resdata;
        try {
            resdata = agentService.search(adminID, agentID);
        } catch (Exception e) {
            return Result.fail(40600, e.getMessage());
        }
        return Result.success(20600, "查询Agent成功", resdata);
    }

    /**
     *
     * @param adminID 管理员id
     * @param agentName agent名字，可空，如果为空，则全量查询
     */
    @GetMapping("/getlist")
    @ResponseBody
    @ApiOperation("查询agent默认列表")
    public Result getList(
            @RequestParam("adminID") @ApiParam(value = "管理员用户id", required = true) int adminID,
            @RequestParam("agentName") @ApiParam(value = "agent名称") String agentName
    ) {

        List<AgentBO> agentList = new ArrayList<>();
        try {
            agentList = agentService.getList(adminID, agentName);
        } catch (Exception e) {
            return Result.fail(40601, e.getMessage());
        }
        return Result.success(20601, "查询Agent成功", agentList);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation("删除Agent")
    public Result deleteAgent(
            @RequestParam("adminID") @ApiParam(value = "管理员用户id", required = true) int adminID,
            @RequestParam("agentID") @ApiParam(value = "要删除的Agent id", required = true) int agentID
    ) {
        try {
            agentService.deleteAgent(adminID, agentID);
        } catch (Exception e) {
            return Result.fail(40601, e.getMessage());
        }
        return Result.success(20601, "删除Agent成功");
    }

    /**
     * agentDatabase = {"agentName":电信，"QA":电信史,"scene":问答,"词表":禁用敏感词库、电信词库}
     */
    @PutMapping("/addnew")
    @ResponseBody
    @ApiOperation("新增Agent")
    public Result newAgent(
            @RequestParam("adminID") @ApiParam(value = "管理员用户id", required = true) int adminID,
            @RequestParam("agentName") @ApiParam(value = "要修改的Agent 名字", required = true) String agentName,
            @RequestParam("modelType") @ApiParam(value = "模型类型", required = true) int modelType,
            @RequestParam("modelIds") @ApiParam(value = "模型id", required = true) List<String> modelIds
    ) {
        Integer agentID = -1;
        try {
            agentID = agentService.newAgent(adminID, agentName, modelType, modelIds);
        } catch (Exception e) {
            return Result.fail(40602, e.getMessage());
        }
        return Result.success(20602, "新增Agent成功", agentID);
    }

    @PutMapping("/change")
    @ResponseBody
    @ApiOperation("修改Agent")
    public Result changeAgent(
            @RequestParam("adminID") @ApiParam(value = "管理员用户id", required = true) int adminID,
            @RequestParam("agentID") @ApiParam(value = "要修改的Agent id", required = true) int agentID,
            @RequestParam("agentName") @ApiParam(value = "要修改的Agent 名字", required = true) String agentName,
            @RequestParam("modelType") @ApiParam(value = "模型类型", required = true) int modelType,
            @RequestParam("modelIds") @ApiParam(value = "模型id", required = true) List<String> modelIds
    ) {
        try {
            agentService.changeAgent(adminID, agentID, agentName, modelType, modelIds);
        } catch (Exception e) {
            return Result.fail(40603, e.getMessage());
        }
        return Result.success(20603, "修改Agent成功", agentID);
    }

}
