package com.example.moviecommu.service;

import com.example.moviecommu.dto.UserDto;
import com.example.moviecommu.dto.UserPageResponseDto;
import com.example.moviecommu.entity.Following;
import com.example.moviecommu.entity.User;
import com.example.moviecommu.repository.FollowingRepository;
import com.example.moviecommu.repository.UserRepository;
import com.example.moviecommu.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;
    private final FollowingRepository followingRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean join(UserDto userDto) {
        User user = userRepository.findById(userDto.getId());
        if(user != null)
            return false;

        User nUser = User.builder()
                .id(userDto.getId())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.saveUser(nUser);
        return true;
    }

    public boolean delete(String username) {
        if(!username.equals(userUtil.getCurrentUsername()) && !userUtil.getCurrentUserRole().equals("ROLE_ADMIN"))
            return false;
        User user = userRepository.findById(username);
        if(user==null)
            return false;
        userRepository.deleteById(username);
        return true;
    }

    public UserPageResponseDto getAll(int size, int page) {
        Map<String, Object> params = new HashMap<>();
        page = size * page;
        params.put("size", size);
        params.put("page", page);

        long total = userRepository.userTotal();
        List<User> userList = userRepository.getAll(params);

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDtoList.add(userDto);
        }

        UserPageResponseDto userPageResponseDto = new UserPageResponseDto();
        userPageResponseDto.setUsers(userDtoList);
        userPageResponseDto.setUserCnt(total);

        return userPageResponseDto;
    }

    public boolean adminJoin(UserDto userDto) {
        User user = userRepository.findById(userDto.getId());
        if(user != null)
            return false;

        User nUser = User.builder()
                .id(userDto.getId())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .role("ROLE_ADMIN")
                .build();

        userRepository.saveUser(nUser);
        return true;
    }

    public boolean follow(String username) {
        User following = userRepository.findById(username);
        String user = userUtil.getCurrentUsername();
        if(user == null || following == null)
            return false;

        Following follow = Following.builder()
                .userId(user)
                .flwName(following.getId())
                .build();
        if(followingRepository.findByFlw(follow) == 0)
            followingRepository.save(follow);
        else  followingRepository.deleteByFlw(follow);
        return true;
    }

    public UserPageResponseDto getFollowingList(int size, int page) {
        Map<String, Object> params = new HashMap<>();
        String userId = userUtil.getCurrentUsername();

        page = size * page;
        params.put("size", size);
        params.put("page", page);
        params.put("userId", userId);

        long total = followingRepository.flwTotal(userId);
        List<User> flwList = followingRepository.findByUserId(params);

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : flwList) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDtoList.add(userDto);
        }

        UserPageResponseDto userPageResponseDto = new UserPageResponseDto();
        userPageResponseDto.setUsers(userDtoList);
        userPageResponseDto.setUserCnt(total);

        return userPageResponseDto;
    }
}