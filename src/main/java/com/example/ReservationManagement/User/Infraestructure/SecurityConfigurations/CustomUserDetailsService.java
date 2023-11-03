package com.example.ReservationManagement.User.Infraestructure.SecurityConfigurations;



import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.IUserRepository;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final IUserRepository _userRepository;

    public CustomUserDetailsService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var User = _userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));

        return new User(User.getUsername(),
                User.getPassword(),
                true,
                true,
                true,
                true,
                mappedRoles(User.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mappedRoles(Set<RoleEntity> roles){
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_"+rol.getName()))
                .collect(Collectors.toList());
    }


}
