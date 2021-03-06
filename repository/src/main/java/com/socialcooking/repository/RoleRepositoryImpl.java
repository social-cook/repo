package com.socialcooking.repository;

import com.socialcooking.domain.Role;
import com.socialcooking.repository.api.RoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleRepositoryImpl extends GenericRepositoryImpl<Role> implements RoleRepository{
}
