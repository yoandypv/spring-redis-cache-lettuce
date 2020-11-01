package com.ejemplos.redis.springrediscache.service;

import com.ejemplos.redis.springrediscache.config.CacheConfig;
import com.ejemplos.redis.springrediscache.entities.User;
import com.ejemplos.redis.springrediscache.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User save(User user) {
    	return this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
    public User findById(Long id) {
        return this.userRepository.findUser(id).orElse(null);
    }

    @CachePut(cacheNames = CacheConfig.USER_CACHE, key = "#id", unless = "#result == null")
    public User updateSalary(Long id, float salary) {
        int res = this.userRepository.updateSalary(id, salary);
        return res > 0 ? this.userRepository.findById(id).orElse(null): null;
    }

    @CacheEvict(cacheNames = CacheConfig.USER_CACHE, key = "#id")
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
