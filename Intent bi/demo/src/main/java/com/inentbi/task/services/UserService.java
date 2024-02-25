package com.inentbi.task.services;

import com.inentbi.task.model.User;

public interface UserService {

	public Boolean login(User user);

	public Boolean signup(User user);

}
