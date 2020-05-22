package com.adiSuper.autha.authorization;

import com.adiSuper.autha.authorization.policy.PolicyEnforcement;
import com.adiSuper.autha.model.Resource;
import com.adiSuper.autha.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class AbacPermissionEvaluator implements PermissionEvaluator {

    private static Logger logger = LoggerFactory.getLogger(AbacPermissionEvaluator.class);

    @Autowired
    PolicyEnforcement policy;

    @Autowired
    UserRepository userRepository;

    private static List<String> resourceNames = Arrays.asList(Resource.values()).stream()
            .map(value -> value.toString()).collect(Collectors.toList());

    @Override
    public boolean hasPermission(Authentication authentication , Object targetDomainObject, Object permission) {
        Object user = authentication.getPrincipal();
        Map<String, Object> environment = new HashMap<>();
        environment.put("time", new Date());

        logger.debug("hasPersmission({}, {}, {})", user, targetDomainObject, permission);
        return policy.check(user, targetDomainObject, permission, environment);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (targetId instanceof UUID && resourceNames.contains(targetType.trim().toUpperCase()))
        {
                Resource resource = Resource.valueOf(targetType.trim().toUpperCase());
                Class resourceClass = resource.getResourceClass();
                Object targetDomainObject = userRepository.getOne((UUID)targetId);
                return hasPermission(authentication, targetDomainObject, permission);
        }
        return false;
    }

}