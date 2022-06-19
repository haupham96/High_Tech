package jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String username;
    private String password;

//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("123"));
//    }
}
