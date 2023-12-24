package com.educationsystem.demo.exception;

import com.educationsystem.demo.tool.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//自定义异常
@ControllerAdvice
public class GlobalException {
//    获取异常
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {
//        return Result.error("500", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

}
