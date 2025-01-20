package com.malgn.ontimeapi.domain.role.service.v1;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.ontimeapi.domain.role.entity.Role;
import com.malgn.ontimeapi.domain.role.model.RoleResponse;
import com.malgn.ontimeapi.domain.role.model.v1.RoleV1Response;
import com.malgn.ontimeapi.domain.role.repository.RoleRepository;
import com.malgn.ontimeapi.domain.role.service.RoleService;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RoleV1Service implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public List<RoleResponse> getAll() {

        List<Role> result = roleRepository.findAll();

        return result.stream().map(role -> RoleV1Response.from(role, true)).toList();
    }
}
