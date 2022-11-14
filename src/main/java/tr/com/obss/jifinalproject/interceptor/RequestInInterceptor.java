package tr.com.obss.jifinalproject.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class RequestInInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startDate",new Date());
        LOGGER.info("Request interceptor started: {} {}",request.getRequestURI(),request.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        var startDate = (Date) request.getAttribute("startDate");
        var endDate = new Date();
        var elapsed = endDate.getTime() - startDate.getTime();
        LOGGER.info("Request interceptor finished: {} {} {}",request.getRequestURI(),
                request.getMethod(),
                elapsed);
    }

}
