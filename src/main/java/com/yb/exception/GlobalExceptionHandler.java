package com.yb.exception;


import com.yb.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BaseException.class})
    @ResponseBody
    public final R handleException(BaseException ex) {
        return R.fail(ex.getCode(),ex.getMessage());
    }
}
