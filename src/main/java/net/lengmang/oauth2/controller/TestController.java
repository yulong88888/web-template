package net.lengmang.oauth2.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
public class TestController {

    @RequestMapping("/test")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @RequestMapping("/jwt")
    public Object getJwtInfo(Authentication authentication, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(header.lastIndexOf("bearer") + 7);
        return Jwts.parser().setSigningKey("lengmang".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
