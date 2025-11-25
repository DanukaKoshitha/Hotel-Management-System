package org.lms.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class JwtService {

    @Value("${public.key.string}")
    private String publicStringKey;

    public String getEmail(String token){
        try{
            byte[] keyBytes = Base64.getDecoder().decode(publicStringKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(keySpec);

            Jws<Claims> claimsJwt = Jwts.parserBuilder().setSigningKey(publicKey)
                    .build().parseClaimsJws(token);

            Claims body = claimsJwt.getBody();
            return body.get("email", String.class);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    public List<String> getRoles(String token){
        try{
            byte[] keyBytes = Base64.getDecoder().decode(publicStringKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(keySpec);

            Jws<Claims> claimsJwt = Jwts.parserBuilder().setSigningKey(publicKey)
                    .build().parseClaimsJws(token);

            Claims body = claimsJwt.getBody();
            Map<String, List<String>> realmAccess = (Map<String, List<String>>) body.get("realm_access");
            return realmAccess.get("roles");
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
