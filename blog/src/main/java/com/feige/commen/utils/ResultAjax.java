package com.feige.commen.utils;

import com.feige.commen.constants.HttpStatus;

import java.util.HashMap;

public class ResultAjax extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;
    //状态码
    public static final String CODE_TAG = "code";
    //返回的消息
    public static final String MSG_TAG = "msg";
    //数据对象
    public static final String DATA_TAG = "data";
    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public ResultAjax(){

    }
    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public ResultAjax(int code,String msg){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * @param code
     * @param msg
     * @param data
     */
    public ResultAjax(int code, String msg, Object data){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        if(StringUtils.isNotNull(data)){
            super.put(DATA_TAG,data);
        }
    }

    /**
     * 返回一个成功消息
     * @param msg
     * @param data
     * @return
     */
    public static ResultAjax success(String msg, Object data){
        return new ResultAjax(HttpStatus.SUCCESS,msg,data);
    }

    /**
     * 返回一个成功消息
     * @param msg
     * @return
     */
    public static ResultAjax success(String msg){
        return ResultAjax.success(msg,null);
    }

    /**
     * 返回一个成功消息
     * @param data
     * @return
     */
    public static ResultAjax success(Object data){
        return ResultAjax.success("操作成功",data);
    }

    /**
     * 返回一个成功消息
     * @return
     */
    public static ResultAjax success(){
        return ResultAjax.success("操作成功");
    }

    /**
     * 返回一个错误消息
     * @param msg
     * @param data
     * @return
     */
    public static ResultAjax error(String msg, Object data){
        return new ResultAjax(HttpStatus.ERROR,msg,data);
    }

    /**
     * 返回一个错误消息
     * @param msg
     * @return
     */
    public static ResultAjax error(String msg){
        return ResultAjax.error(msg,null);
    }
    /**
     * 返回一个错误消息
     * @param data
     * @return
     */
    public static ResultAjax error(Object data){
        return ResultAjax.error("操作失败",data);
    }
    /**
     * 返回一个错误消息
     * @return
     */
    public static ResultAjax error(){
        return ResultAjax.error("操作失败");
    }
}
