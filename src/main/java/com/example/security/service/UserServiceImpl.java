package com.example.security.service;

import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           @Lazy BCryptPasswordEncoder bCryptpasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptpasswordEncoder = bCryptpasswordEncoder;

    }



    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setPassword(bCryptpasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    @Transactional
    @Override
    public void update(User updatedUser) {
        if (!updatedUser.getPassword().equals(findById(updatedUser.getId()).getPassword())) {
            updatedUser.setPassword(bCryptpasswordEncoder.encode(updatedUser.getPassword()));
        }
        updatedUser.setRoles(updatedUser.getRoles());
        userRepository.save(updatedUser);
    }



    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuth(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuth(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
