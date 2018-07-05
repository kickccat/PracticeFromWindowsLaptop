package io.sample.springdemo.interceptors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class VisitorInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("currentTime", LocalDateTime.now());
        
        HttpSession session = request.getSession(true);
        if (session.getAttribute("sessionStartTime") == null) {
            session.setAttribute("sessionStartTime", LocalDateTime.now());
        }
        return true;
    }
}
