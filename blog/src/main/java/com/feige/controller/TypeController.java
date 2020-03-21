package com.feige.controller;


import com.feige.common.constants.Constants;
import com.feige.common.utils.ResultAjax;
import com.feige.common.utils.SelectParam;
import com.feige.common.utils.StringUtils;
import com.feige.pojo.Type;
import com.feige.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "类型的增删改查接口")
@RestController
@RequestMapping("/api/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     *
     * @param page
     * @param limit
     * @param searchContent
     * @return
     */
    @ApiOperation(value = "按条数查询类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true),
            @ApiImplicitParam(name = "limit",value = "每页条数",required = true),
            @ApiImplicitParam(name = "searchContent",value = "搜索内容")
    })
    @GetMapping("/get_types")
    public ResultAjax getTypes(Integer page, Integer limit, String searchContent){

        List<Type> list = typeService.getTypes(new SelectParam(page,limit,searchContent));
        int typeCount = typeService.getCount(searchContent);
        return ResultAjax.success(list,typeCount);
    }

    /**
     *
     * @param typeName
     * @return
     */
    @ApiOperation(value = "增加一个类型")
    @ApiImplicitParam(name = "typeName",value = "类型名",required = true)
    @PostMapping("/add_type")
    public ResultAjax addType(String typeName){
        Type type = typeService.getType(typeName);
        if (StringUtils.isNull(type)){
            int add = typeService.addType(new Type(0, typeName));
            if (add == 1){
                return ResultAjax.success();
            }else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }
    }

    /**
     *
     * @param typeName
     * @return
     */
    @ApiOperation(value = "删除一个类型")
    @ApiImplicitParam(name = "typeName",value = "类型名",required = true)
    @DeleteMapping("/delete_type/{typeName}")
    public ResultAjax deleteType(@PathVariable("typeName") String typeName){
        int delete = typeService.deleteType(typeName);
        if (delete == 1){
            return ResultAjax.success();
        }else {
            return ResultAjax.error();
        }
    }

    /**
     *
     * @param id
     * @param typeName
     * @return
     */
    @ApiOperation(value = "修改一个类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "ID",required = true),
            @ApiImplicitParam(name = "typeName",value = "类型名",required = true)
    })
    @PutMapping("/update_type/{id}")
    public ResultAjax updateType(@PathVariable("id") Integer id,String  typeName){
        if (typeService.getTypeById(id).getTypeName().equals(typeName) || StringUtils.isNull(typeService.getType(typeName))){
            int update = typeService.updateType(new Type(id, typeName));
            if (update == 1) {
                return ResultAjax.success();
            }else {
                return ResultAjax.error();
            }
        }else {
            return ResultAjax.error(Constants.EXIST);
        }
    }
}
