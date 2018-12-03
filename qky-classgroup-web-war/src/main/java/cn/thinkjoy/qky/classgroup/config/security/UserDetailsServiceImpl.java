package cn.thinkjoy.qky.classgroup.config.security;

import cn.hutool.core.bean.BeanUtil;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import cn.thinkjoy.qky.classgroup.param.bas.BasUserParam;
import cn.thinkjoy.qky.classgroup.service.bas.IBasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IBasUserService basUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BasUserParam basUserParam = new BasUserParam();
        basUserParam.setPhoneNumber(username);
        BasUser basUser = (BasUser) basUserService.queryOne(basUserParam.toMap());
        BasUserEx user = new BasUserEx();
        BeanUtil.copyProperties(basUser,user);
        return new SecurityUserDetails(user);
    }
}
