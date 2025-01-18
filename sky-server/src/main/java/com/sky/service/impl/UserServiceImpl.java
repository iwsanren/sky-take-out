package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    // wechat service API url : retrieve user's openid
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    private String grantType = "authorization_code";

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    /**
     * wechat login
     * @param userLoginDTO
     * @return
     */
    public User wxlogin(UserLoginDTO userLoginDTO) {
        // 1. Call the WeChat API service to retrieve the current user's openid.
        String openid = getOpenid(userLoginDTO.getCode());

        // 2. Check if the openid is empty. If it is, the login is considered unsuccessful.
        if(openid == null){
//            System.out.println("openid is null");
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        // 3. Determine whether the current user's openid belongs to a new user (i.e., check if the openid exists in the user table).
        User user = userMapper.getByOpenId(openid);

        // 4. If it is a new user, automatically complete the registration process.
        if(user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        // 5. Return the user object.
        return user;
    }

    /**
     * Call the WeChat API service to retrieve the current user's openid.
     * @param code
     * @return
     */
    // Call the WeChat API service to retrieve the current user's openid.
    private String getOpenid(String code){
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", grantType);

        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject((json));
        String openid =jsonObject.getString("openid");
//        System.out.println("openid is:" + openid);
        return openid;
    }

}
