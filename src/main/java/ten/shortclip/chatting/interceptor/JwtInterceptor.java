package ten.shortclip.chatting.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ten.shortclip.chatting.Exception.UnauthorizedException;
import ten.shortclip.chatting.service.JwtService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    private JwtService jwtService;

    public JwtInterceptor(JwtService jwtService){
        this.jwtService = jwtService;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        final String token = request.getHeader(HEADER_AUTH);
        if(token != null && jwtService.isValid(token)) return true;
        else throw new UnauthorizedException("JwtInterceptor.preHandle()");
    }
}
