package com.example.ucenter.config.shiro;

import com.example.EurekaUcseviceApplication;
import com.example.bean.ActiveUser;
import com.example.bean.ResponseBean;
import com.example.config.jwt.JWTToken;
import com.example.utils.AddressUtil;
import com.example.utils.TokenUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.apache.tomcat.jni.Address;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JWT过滤器
 */
@Component
@Slf4j

public class JWTFilter extends BasicHttpAuthenticationFilter {

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    private final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    /**
     * 认证之前执行该方法
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        return  null != subject && subject.isAuthenticated();
    }

    /**
     * 认证未通过执行该方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1.检查请求头中是否含有token
        HttpServletResponse responses = (HttpServletResponse) response;
        responses.setHeader("Access-Control-Allow-Origin","*");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token =httpServletRequest.getHeader("token");



           //httpServletRequest.getHeader("Authorization");
        //2.如果客户端没有携带token，拦下请求
        if(null==token|| "".equals(token)){
            responseTokenError(response,"Token无效，您无权访问该接口");
            return false;
        }
        //3.如果有，对token进行验证
        JWTToken jwtToken = new JWTToken(token);
        try{
            SecurityUtils.getSubject().login(jwtToken);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseTokenError(response,e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 对跨域进行支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest =(HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Expose-Headers","Cache-Control,Content-Type,Expires,Pragma,Content-Language,Last-Modified,token");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","token,content-type");
        httpServletResponse.setHeader("Authorization","123"); //设置响应头
        httpServletResponse.setHeader("token","*"); //设置响应头
        //跨域时会首先发送一个option请求，这里给option请求返回正在状态
        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request,response);
    }

    private void responseTokenError(ServletResponse response, String msg){
        HttpServletResponse httpServletResponse= WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; character=UTF-8");
        try(PrintWriter out = httpServletResponse.getWriter()){
            String data =new Gson().toJson(new ResponseBean(4001,msg,null));
            out.append(data);
        }catch (IOException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
