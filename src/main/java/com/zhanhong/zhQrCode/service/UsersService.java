package com.zhanhong.zhQrCode.service;

import com.zhanhong.zhQrCode.entity.Users;

public interface UsersService {

	/**
	 * 保存
	 */
	public String inserUsers(Users users);

	Users queryUsersById(String Id);

	public int CheckUsers(Users users);
}