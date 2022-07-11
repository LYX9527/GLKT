package com.orange.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.orange.vod.domain.TempUser;
import com.orange.vod.service.TempUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.service.impl
 * @className : JWTUserDetailsService
 * @description:
 * @date : 2022/7/11 8:31
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private TempUserService tempUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<TempUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TempUser::getName, username);
        TempUser one = tempUserService.getOne(queryWrapper);
        if (one.getName().equals(username)) {
            return new User(one.getName(), one.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}