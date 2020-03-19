package com.feige.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feige.pojo.Type;
import com.feige.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/getTypes")
    public String getTypes(Integer page,Integer limit,String searchContent){
        HashMap<String,Object> map = new HashMap<>();
        map.put("page",(page-1)*limit);
        map.put("count",limit);
        map.put("searchContent",searchContent);
        List<Type> list = typeService.getTypes(map);
        int typeCount = typeService.getCount(map);
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        //将我们的对象解析成为json格式
        try {
            //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
            String str = "{\"code\":0,\"msg\":\"\",\"count\":" + typeCount + ",\"data\":" + mapper.writeValueAsString(list) + "}";
            return str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }
    @RequestMapping("/addType")
    public String addType(String typeName){
        String status = null;

        Type type1 = new Type(0,typeName);
        Type type2 = typeService.getType(typeName);
        if(type2 == null){
            int  add = typeService.addType(type1);
            if(add == 1){
                status = "SUCCESS";
            }
        }else {
            status = "exist";
        }
        return status;
    }
    @RequestMapping("/deleteType/{typeName}")
    public String deleteType(@PathVariable("typeName") String typeName){
        String status = null;
        int delete = typeService.deleteType(typeName);
        if(delete == 1){
            status = "SUCCESS";
        }
        return status;
    }
    @RequestMapping("/updateType/{id}/{oldTypeName}")
    public String updateType(@PathVariable("oldTypeName") String oldTypeName,@PathVariable("id") Integer id,String  typeName){
        String status = null;
        Type type1 = new Type(id,typeName);
        if(typeName.equals(oldTypeName)){
            int update = typeService.updateType(type1);
            if(update == 1){
                status = "SUCCESS";
            }
        }else {
            Type type2 = typeService.getType(typeName);
            if(type2 == null){
                int update = typeService.updateType(type1);
                if(update == 1){
                    status = "SUCCESS";
                }
            }else {
                status = "exist";
            }
        }
        return status;
    }
    @RequestMapping("/getType")
    public String getType(String typeName){
        ObjectMapper mapper = new ObjectMapper();
        Type type = typeService.getType(typeName);
        String str = null;
        try {
            str = mapper.writeValueAsString(type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;

    }
}
