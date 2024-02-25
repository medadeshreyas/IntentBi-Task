package com.inentbi.task.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inentbi.task.model.User;
import com.inentbi.task.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Boolean login(User user) {
		Optional<User> exists = userRepository.findByUsername(user.getUsername());
		if (exists.isPresent()) {
			return exists.get().getPassword().equals(user.getPassword());
		}
		return false;

	}

	@Override
	public Boolean signup(User user) {
		Optional<User> exists = userRepository.findByUsername(user.getUsername());
		if (exists.isPresent())
			return false;
		userRepository.save(user);
		return true;
	}

}
