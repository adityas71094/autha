package com.adiSuper.autha.user;

import com.adiSuper.generated.core.tables.pojos.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
