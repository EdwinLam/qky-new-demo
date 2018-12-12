package cn.ripple.config.security;


import cn.hutool.core.bean.BeanUtil;
import cn.ripple.entity.user.UserInfo;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SecurityUserDetails extends UserInfo implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 1L;

    public SecurityUserDetails(UserInfo userInfo) {
        if(userInfo!=null) {
            BeanUtil.copyProperties(userInfo,this);
            this.setUsername(userInfo.getUsername());
            this.setPassword(userInfo.getPassword());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        return authorityList;
    }


    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {

    }
}
