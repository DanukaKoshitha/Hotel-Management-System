package org.lms.service.Impl;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.account.UserRepresentation;
import org.lms.config.KeycloakSecurityUtil;
import org.lms.dto.request.SystemUserRequestDto;
import org.lms.exception.BadRequestException;
import org.lms.repository.SystemUserRepo;
import org.lms.service.SystemUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepo systemUserRepo;
    private final KeycloakSecurityUtil securityUtil;

    @Override
    public void createUser(SystemUserRequestDto userRequestDto) {
        if (userRequestDto.getFirstName() == null || userRequestDto.getFirstName().trim().isEmpty()){
            throw new BadRequestException("First name is required");
        }

        if (userRequestDto.getLastName() == null || userRequestDto.getLastName().trim().isEmpty()){
            throw new BadRequestException("Last name is required");
        }

        if (userRequestDto.getEmail() == null || userRequestDto.getEmail().trim().isEmpty()){
            throw new BadRequestException("Email is required");
        }

        String userId = "";
        String otp = "";
        Keycloak keycloak = null;

        UserRepresentation user = null;
        keycloak = securityUtil.getKeycloak();


    }
}
