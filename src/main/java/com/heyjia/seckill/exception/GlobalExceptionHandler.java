package com.heyjia.seckill.exception;

import com.heyjia.seckill.result.CodeMsg;
import com.heyjia.seckill.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
//@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e) {
        if (e instanceof  GlobalException) {
            logger.info("遇见异常");
            GlobalException globalException = (GlobalException) e;
            CodeMsg msg =globalException.getCm();
            return Result.error(msg);
        }
        return Result.success("");
    }
}
