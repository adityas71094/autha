package com.adiSuper.autha.authorization.policy;

public interface PolicyEnforcement {

    boolean check(Object subject, Object resource, Object action, Object environment);

}
