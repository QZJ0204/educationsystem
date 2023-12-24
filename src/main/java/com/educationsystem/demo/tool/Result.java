package com.educationsystem.demo.tool;

import lombok.*;

import javax.annotation.sql.DataSourceDefinition;

//前后端交互统一响应结果
@Data
//自动构造所有属性的构造函数
@AllArgsConstructor
//自动构造所有无参构造函数
@NoArgsConstructor
public class Result {
    public static final String CODE_SUCCESS ="200";
    public static final String CODE_AUTH_ERROR ="401";
    public static final String CODE_SYS_ERROR ="500";

//    请求的返回编码，表示请求情况
    private  String code;
//    msg表示错误的详细信息
    private  String msg;
//    数据返回的类型和详细内容
    private Object data;

    public static Result sucess(){
        return new Result (CODE_SUCCESS,"请求成功",null);
    }
    public static Result sucess(Object data){
        return new Result (CODE_SUCCESS,"请求成功",data);
    }
    public static Result error(String msg){
        return new Result (CODE_SYS_ERROR,msg,null);
    }
    public static Result error(String code,String msg){
        return new Result (code,msg,null);
    }
    public static Result error(){
        return new Result (CODE_SYS_ERROR,"系统错误",null);
    }
}
