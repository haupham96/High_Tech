package jwt.config;

import jwt.model.Account;
import jwt.service.IAccountService;
import jwt.service.impl.AccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private String jwt;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AccountDetailsServiceImpl accountDetailsServiceImpl;

    @Autowired
    IAccountService iAccountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = this.getTokenFromRequest(request);
            this.jwt = token;
            String userName = this.jwtUtil.getUserNameFromJwt(token);

            UserDetails userDetails = this.accountDetailsServiceImpl.loadUserByUsername(userName);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            logger.error("Cann't authentication : {} ", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    public Account findAccountByToken() {
        String userName = jwtUtil.getUserNameFromJwt(this.jwt);
        return this.iAccountService.findByUserName(userName);
    }

}
