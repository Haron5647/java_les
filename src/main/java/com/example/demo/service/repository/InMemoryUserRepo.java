package com.example.demo.service.repository;

import com.example.demo.service.entity.UserData;
import com.example.demo.service.entity.UserRegisterData;
import com.example.demo.service.repository.UserRepository;

public class InMemoryUserRepo implements UserRepository {

    @Override
    public int addUser(UserRegisterData user) {
        return 0;
    }

    @Override
    public UserData getUser(int id) {
        return null;
    }
}
