package com.yf.exam.core.exception;

import com.yf.exam.core.api.ApiRest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 统一异常处理类
 * @author bool 
 * @date 2019-06-21 19:27
 */
@RestControllerAdvice
public class ServiceExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initWebBinder(WebDataBinder binder){

    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttribute(Model model) {
        
    }

    /**
     * 捕获ServiceException
     * @param e
     * @return
     */
    @ExceptionHandler({com.yf.exam.core.exception.ServiceException.class})
    @ResponseStatus(HttpStatus.OK)
    public ApiRest serviceExceptionHandler(ServiceException e) {
        return new ApiRest(e);
    }

}