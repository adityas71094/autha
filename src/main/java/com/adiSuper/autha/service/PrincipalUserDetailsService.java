package com.adiSuper.autha.service;

import com.adiSuper.autha.model.PrincipalUser;
import com.adiSuper.autha.model.User;
import com.adiSuper.autha.repository.UserRepository;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.adiSuper.generated.core.Tables.AUTHORITIES;
import static org.jooq.impl.DSL.field;

@Service("principalUserDetailsService")
public class PrincipalUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DSLContext db;

    @Override
    public PrincipalUser loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User>optionalUser = userRepository.findOneByUsername(username);

        if (optionalUser.isPresent())
        {
            User user = optionalUser.get();
            UUID id = user.getId();

            Set<GrantedAuthority> authorities = getGrantedAuthoritySetByUserId(id);
            PrincipalUser userDetails = new PrincipalUser(user, authorities);
            return userDetails;
        }
        else
        {
            throw new UsernameNotFoundException("User with username :: " + username + " not found.");
        }
    }

    private Set<GrantedAuthority> getGrantedAuthoritySetByUserId(UUID id)
    {
        Result<Record1<String>> record1s =  db.select(AUTHORITIES.AUTHORITY).from(AUTHORITIES)
                .where(field(AUTHORITIES.USER_ID).eq(id)).fetch();

        List<String> userAuthorities = record1s.getValues(field(AUTHORITIES.AUTHORITY));

        Set<GrantedAuthority> authorities = userAuthorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toSet());
        return authorities;
    }
}
