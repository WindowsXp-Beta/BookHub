package com.windowsxp.bookhub.backend.serviceimpl;

import com.windowsxp.bookhub.backend.dao.UserDao;
import com.windowsxp.bookhub.backend.entity.User;
import com.windowsxp.bookhub.backend.enumerate.UserType;
import com.windowsxp.bookhub.backend.exception.UserException;
import com.windowsxp.bookhub.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;


    @Override
    public User login(String username, String rawPassword){
        Optional<User> optionalUser = userDao.checkUser(username);
        if(optionalUser.isEmpty()) throw new UserException(UserException.UserExceptionType.UNAUTHORIZED_USER);
        else {
            User user = optionalUser.get();
            if (!encoder.matches(rawPassword, user.getPassword())) throw new UserException(UserException.UserExceptionType.WRONG_PASSWORD);
            else if (user.getUserType() == UserType.BANNED) throw new UserException(UserException.UserExceptionType.BANNED_USER);
            else return optionalUser.get();
        }
    }

    @Override
    public User getUser(Integer id){
        return userDao.getUser(id);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userDao.getAllUsers(pageable);
    }

    @Override
    public void editUser(Integer userId, UserType userType) {
        User user = userDao.getUser(userId);
        user.setUserType(userType);
        userDao.saveUser(user);
    }

    @Override
    public User register(String username, String rawPassword, String email, String address) {
        User newUser = new User();
        newUser.setUserType(UserType.USER);
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(encoder.encode(rawPassword));
        newUser.setAddress(address);
        userDao.saveUser(newUser);
        return newUser;
    }

    @Override
    public Boolean checkDuplicateUsername(String newUsername){
        List<User.Username> allUsername = userDao.getAllUsername();
        for(User.Username username: allUsername){
            if(newUsername.equals(username.getUsername())) return false;
        }
        return true;
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.deleteUser(userId);
    }
}
