package cn.kmbeast.aop;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.em.RoleEnum;
import cn.kmbeast.service.UserService;
import cn.kmbeast.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 *Interface authentication protection aspect
 */
@Aspect
@Component
public class ProtectorAspect {

    /**
     *Surrounding notifications
     *Before Execution - (Target Operation) - After Execution
     *Surrounding: Intercept at both ends
     *
     * @param proceedingJoinPoint connection point
     * @return Object
     */
    @Around("@annotation(cn.kmbeast.aop.Protector)")
    public Object auth(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        if (token == null) {
            return ApiResult.error("Identity authentication failed, please log in first");
        }
        Claims claims = JwtUtil.fromToken(token);
        if (claims == null) {
            return ApiResult.error("Identity authentication failed, please log in first");
        }
        Integer userId = claims.get("id", Integer.class);
        Integer roleId = claims.get("role", Integer.class);
        // Obtain the signature of the intercepted method
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // Obtain the @ Protector annotation instance on the method
        Protector protectorAnnotation = signature.getMethod().getAnnotation(Protector.class);
        if (protectorAnnotation == null) {
            return ApiResult.error("Identity authentication failed, please log in first");
        }
        String role = protectorAnnotation.role();
        // Verify user roles
        if (!"".equals(role)) {
            if (!Objects.equals(RoleEnum.ROLE(Math.toIntExact(roleId)), role)) {
                return ApiResult.error("No operation permission");
            }
        }
        // Placed in ThreadLocal, all current threads are available
        LocalThreadHolder.setUserId(userId, roleId);
        Object result = proceedingJoinPoint.proceed();
        // Request completed, release resources
        LocalThreadHolder.clear();
        return result;
    }


}
