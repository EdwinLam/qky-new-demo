package cn.thinkjoy.qky.classgroup.config.security;


import cn.hutool.core.bean.BeanUtil;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SecurityUserDetails extends BasUserEx implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 1L;

    public SecurityUserDetails(BasUserEx user) {
        if(user!=null) {
            BeanUtil.copyProperties(user,this);
            if(user.getPhoneNumber()!=null){
                this.setUsername(user.getPhoneNumber());
                String encryptPass = new BCryptPasswordEncoder().encode(user.getPhoneNumber());
                this.setPassword(encryptPass);
            }
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
