package com.easy.archiecture.support;

import com.easy.archiecture.annotation.LogRecord;
import com.easy.archiecture.entity.LogInfo;
import com.easy.archiecture.entity.User;
import com.easy.archiecture.service.LogInfoServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class LogAopImpl {

    @Autowired
    private LogInfoServiceImpl logInfoService;

    @Pointcut("@annotation(com.easy.archiecture.annotation.LogRecord)")
    public void controllerLog() {
    }


    @Around("controllerLog()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if (httpServletRequest == null) {
            return null;
        }
        LogInfo logInfo = new LogInfo();
        /*从切面值入点获取植入点方法*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        /*获取切入点方法*/
        Method method = signature.getMethod();
        /*获取方法上的值*/
        LogRecord systemControllerLog = method.getAnnotation(LogRecord.class);
        Object proceed = joinPoint.proceed();
        /*保存操作事件*/
        if (systemControllerLog != null && proceed != null) {
            String operation = systemControllerLog.operation();
            /*保存日志类型*/
            logInfo.setLogOp(operation);
            String type = systemControllerLog.type();
            logInfo.setLogType(type);
            String token = httpServletRequest.getHeader("token");
            if (StringUtils.isBlank(token)) {
                return null;
            }
            User user = JWTUtil.unsign(token, User.class);
            if (user == null) {
                return null;
            }
            logInfo.setUserId(user.getId());
            logInfo.setUrl(httpServletRequest.getRequestURI());
            logInfo.setCreateTime(System.currentTimeMillis());

            logInfoService.insertLog(logInfo);

        }
        return proceed;
    }


    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra != null ? sra.getRequest() : null;
    }

}
