package jwt.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private Integer id ;
    private String username ;
    private String token;
    private String type = "Bearer";
    private List<String> roles ;

    public JwtResponse(Integer id, String username, String token, List<String> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

}
