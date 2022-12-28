package com.example.service.impl;

import com.example.UserRepository;
import com.example.io.entity.UserEntity;
import com.example.service.UserService;
import com.example.shared.Utils;
import com.example.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);

        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userEntity.setEmailVerificationStatus(false);
        userEntity.setEmailVerificationToken("gdfg");

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
}
