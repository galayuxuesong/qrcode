package com.zhanhong.zhQrCode.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanhong.zhQrCode.entity.Users;
import com.zhanhong.zhQrCode.mapper.UsersMapper;
import com.zhanhong.zhQrCode.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {


	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	UsersMapper usersMapper;

	@Override
	public String inserUsers(Users users) {
	    String  id=IdUtil.simpleUUID();
        users.setId(id);
	    usersMapper.insert(users);
		return id;
	}

	@Override
	public Users queryUsersById(String Id) {

		Users user=new Users();

		if(StrUtil.isBlank(Id)){
			return user;
		}
		try {
			user = usersMapper.selectById(Id);
		}catch (Exception e){
			log.error(e.getMessage());
		}
		return  user;
	}

	@Override
	public int CheckUsers(Users users) {
		Users u=new Users();
		//u.setName(users.getName());
		u.setPhone(users.getPhone());
		List list =usersMapper.selectList(new QueryWrapper<>(u));
		return list.size();
	}


}
