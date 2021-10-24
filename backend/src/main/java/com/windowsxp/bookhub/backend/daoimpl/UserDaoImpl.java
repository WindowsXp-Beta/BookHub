package com.windowsxp.bookhub.backend.daoimpl;

import com.windowsxp.bookhub.backend.dao.UserDao;
import com.windowsxp.bookhub.backend.entity.User;
import com.windowsxp.bookhub.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public Optional<User> checkUser(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser(Integer id){
        return userRepository.getById(id);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User.Username> getAllUsername() {
        return userRepository.findAllProjectionsBy(User.Username.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveAndFlushUser(User user) {
        userRepository.saveAndFlush(user);
    }
}