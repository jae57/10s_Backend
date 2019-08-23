package ten.shortclip.chatting.service.serviceImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ten.shortclip.chatting.exception.UnauthorizedException;
import ten.shortclip.chatting.model.User;
import ten.shortclip.chatting.service.JwtService;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private static final Key secretKey =  Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public <T> String create(String key, T data, String subject){
        String jws = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setExpiration(new Date(System.currentTimeMillis()+(1000*60*60*24))) // 유효기간 : 하루
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(subject)
                .claim(key,data)
                .signWith(secretKey)
                .compact();

        return jws;
    }

    public boolean isValid(String jws){

        try{
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jws);
            return true;
        }catch(JwtException e){
            throw new UnauthorizedException("JwtServiceImpl.isValid()");
        }
    }

    public User get(String key){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jws = request.getHeader("Authorization");
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jws);
        }catch(Exception e){
            throw new UnauthorizedException("JwtServiceImpl.get()");
        }

        Map<String, Object> userValue = (LinkedHashMap<String,Object>)claims.getBody().get(key);
        User user = new User();
        Long id = Long.parseLong(String.valueOf(userValue.get("id")));
        user.setId(id);
        return user;
    }

    public Long getUserId(){
        return get("user").getId();
    }
}
