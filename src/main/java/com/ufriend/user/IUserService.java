package com.ufriend.user;

import java.util.List;

public interface IUserService {
    public List<UserEntity> list();
    public UserEntity findById(Long id);
    public UserEntity findByEmail(String email);
    public UserEntity save(UserEntity user);
    public void delete(UserEntity user);
}
