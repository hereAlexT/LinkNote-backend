//package com.cachenote.server.security;
//
//import com.cachenote.server.payload.entity.UserDoc;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import com.cachenote.server.repository.UserRepository;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //Select user Information from DB
//        UserDoc dbUser = userRepository.findByUsername(username);
//        if (dbUser == null) {
//            throw new UsernameNotFoundException("UserDoc not found");
//        }
//
//
//
//        return new myUserDetails(dbUser);
//    }
//}