package com.moduda.api.moduleapi.user.service;

import com.moduda.api.moduleapi.auth.CustomUserDetails;
import com.moduda.api.moduleapi.user.dto.request.UserRequest;
import com.moduda.api.moduleapi.user.dto.response.UserResponse;
import com.moduda.api.moduleapi.user.exception.DuplicatedUserException;
import com.moduda.api.moduleapi.user.exception.NotFoundUserException;
import com.moduda.api.moduleapi.user.exception.UpdatePasswordFailException;
import com.moduda.core.modulecore.user.entity.User;
import com.moduda.core.modulecore.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void join(UserRequest.Join userRequest){
        checkDuplicatedUserId(userRequest.userId());
        checkDuplicatedEmail(userRequest.email());

        User user = userRequest.toEntity();

        String bcryptPassword = passwordEncoder.encode(userRequest.password());
        user.updatePassword(bcryptPassword);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserResponse.Detail findById(Long id){
        User user = getUserById(id);
        return UserResponse.Detail.from(user);
    }

    @Transactional
    public void delete(CustomUserDetails userDetails){
        Long id = userDetails.getUser().getId();
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponse.Detail updateProfile(CustomUserDetails userDetails, UserRequest.Update userRequest){
        User user = userDetails.getUser();
        if (user.getEmail() != userRequest.email()) {
            checkDuplicatedEmail(userRequest.email());
        }
        user.updateProfile(userRequest.nickname(), userRequest.email(), userRequest.birth());
        return UserResponse.Detail.from(user);
    }

    @Transactional
    public void updatePassword(CustomUserDetails userDetails, UserRequest.UpdatePassword updatePassword){
        User user = userDetails.getUser();
        checkUpdatePassword(user, updatePassword);

        String bcryptPassword = passwordEncoder.encode(updatePassword.newPassword());
        user.updatePassword(bcryptPassword);
    }

    @Transactional
    public void checkDuplicatedUserId(String userId) {
        boolean exists = userRepository.existsByUserId(userId);
        if (exists) {
            throw new DuplicatedUserException();
        }
    }

    @Transactional
    public void checkDuplicatedEmail(String email) {
        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            throw new DuplicatedUserException();
        }
    }

    private void checkUpdatePassword(User user, UserRequest.UpdatePassword updatePassword) {

        if  (updatePassword.oldPassword().equals(updatePassword.newPassword())) {
            throw new UpdatePasswordFailException("현재 비밀번호와 새로운 비밀번호가 동일합니다.");
        }

        if (!passwordEncoder.matches(updatePassword.oldPassword(), user.getEncodedPassword())){
            throw new UpdatePasswordFailException("현재 비밀번호를 잘못 입력하셨습니다.");
        }

        if (!updatePassword.newPassword().equals(updatePassword.confirmPassword())) {
            throw new UpdatePasswordFailException("새 비빌번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }


    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }

}
