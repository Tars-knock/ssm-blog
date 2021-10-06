package cn.tarsknock.controller;

import cn.tarsknock.util.ResponseUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class, UnauthorizedException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response)
    {
            return "error/401";
    }



}
