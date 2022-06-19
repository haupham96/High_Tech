package jwt.service.impl;

import jwt.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AccountDetailsImpl implements UserDetails {
    private static final Long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public AccountDetailsImpl(Integer id, String username, String password, List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public static AccountDetailsImpl build(Account account) {
        List<GrantedAuthority> authorities = account.getAccountRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getRole().getRoleName()))
                .collect(Collectors.toList());
        return new AccountDetailsImpl(account.getId(), account.getUserName(), account.getEncryptPassword(), authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDetailsImpl that = (AccountDetailsImpl) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
