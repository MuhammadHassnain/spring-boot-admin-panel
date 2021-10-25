package com.adminpanel.security.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adminpanel.security.user.entity.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
