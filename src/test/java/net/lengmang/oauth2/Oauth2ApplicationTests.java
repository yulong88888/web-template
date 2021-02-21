package net.lengmang.oauth2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.lengmang.oauth2.entity.StudentEntity;
import net.lengmang.oauth2.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
class Oauth2ApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成JWT
     */
    @Test
    void jwtEncode() {
        long currentTimeMillis = System.currentTimeMillis();
        long exp = currentTimeMillis + 60 * 1000;
        JwtBuilder jwtBuilder = Jwts.builder()
                // 唯一id
                .setId("888")
                // 接受用户
                .setSubject("666")
                // 签发时间
                .setIssuedAt(new Date())
                // 失效时间
                .setExpiration(new Date(exp))
                // 签名
                .signWith(SignatureAlgorithm.HS256, "lengmang");
        String compact = jwtBuilder.compact();
        System.out.println(compact);
    }

    /**
     * 自定义生成JWT
     */
    @Test
    void jwtCustomer() {
        long currentTimeMillis = System.currentTimeMillis();
        long exp = currentTimeMillis + 60 * 1000;
        JwtBuilder jwtBuilder = Jwts.builder()
                // 唯一id
                .setId("888")
                // 接受用户
                .setSubject("666")
                // 签发时间
                .setIssuedAt(new Date())
                // 失效时间
                .setExpiration(new Date(exp))
                // 自定义声明 或者 .addClaims(<map>)
                .claim("fuck","you")
                // 签名
                .signWith(SignatureAlgorithm.HS256, "lengmang");
        String compact = jwtBuilder.compact();
        System.out.println(compact);
    }

    /**
     * 解密JWT
     * JWT过期后会抛出异常
     */
    @Test
    void jwtDecode() {
        String str = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiI2NjYiLCJpYXQiOjE2MTM3NDMxMzQsImV4cCI6MTYxMzc0MzE5NCwiZnVjayI6InlvdSJ9.o7J6NGYAxhN6F6Iyfj9xY8s-J5Tw9UWN9ehRxvAMBtM";
        Claims claims = (Claims) Jwts.parser().setSigningKey("lengmang").parse(str).getBody();

        System.out.println("id=" + claims.getId());
        System.out.println("sub=" + claims.getSubject());
        System.out.println("iat=" + claims.getIssuedAt());
        System.out.println("customer=" + claims.get("fuck"));
    }

    @Autowired
    StudentRepository studentRepository;

    @Test
    void crud(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUsername("0.0");
        studentEntity.setPassword("123");
        studentEntity.setAvatar("");
        studentEntity.setBirthday(new Timestamp(System.currentTimeMillis()));
        studentRepository.save(studentEntity);
    }

}
