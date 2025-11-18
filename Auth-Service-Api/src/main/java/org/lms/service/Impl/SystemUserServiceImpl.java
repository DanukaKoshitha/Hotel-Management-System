package org.lms.service.Impl;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.lms.config.KeycloakSecurityUtil;
import org.lms.dto.request.SystemUserRequestDto;
import org.lms.entity.Otp;
import org.lms.entity.SystemUser;
import org.lms.exception.BadRequestException;
import org.lms.exception.DuplicateEntryException;
import org.lms.repository.OtpRepo;
import org.lms.repository.SystemUserRepo;
import org.lms.service.EmailService;
import org.lms.service.SystemUserService;
import org.lms.util.OtpGenarater;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepo systemUserRepo;
    private final OtpRepo otpRepo;
    private final KeycloakSecurityUtil securityUtil;
    private final OtpGenarater otpGenerator;
    private final EmailService emailService;

    @Value("${keycloak.config.realm}")
    private String realm;

    @Override
    public void createUser(SystemUserRequestDto userRequestDto) throws IOException {
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

        UserRepresentation existingUser = null;
        keycloak = securityUtil.getKeycloak();

        existingUser = keycloak.realm(realm).users().search(userRequestDto.getEmail()).stream().findFirst().orElse(null);

        if (existingUser != null) {
            Optional<SystemUser> selectedSystemUserFromAuthService =
                    systemUserRepo.findByEmail(userRequestDto.getEmail());
            if (selectedSystemUserFromAuthService.isEmpty()) {
                keycloak.realm(realm).users().delete(existingUser.getId());
            } else {
                throw new DuplicateEntryException("Email already exists");
            }
        } else {
            Optional<SystemUser> selectedSystemUserFromAuthService =
                    systemUserRepo.findByEmail(userRequestDto.getEmail());

            if (selectedSystemUserFromAuthService.isPresent()) {
                Optional<Otp> selectedOtp =
                        otpRepo.findBySystemUserId(selectedSystemUserFromAuthService.get().getUserId());
                if (selectedOtp.isPresent()) {
                    otpRepo.deleteById(selectedOtp.get().getPropertyId());
                }
                systemUserRepo.deleteById(selectedSystemUserFromAuthService.get().getUserId());
            }
        }
            UserRepresentation userRepresentation = mapUserRepo(userRequestDto, false, false);
            Response response = keycloak.realm(realm).users().create(userRepresentation);
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                RoleRepresentation userRole = keycloak.realm(realm).roles().get("user").toRepresentation();
                userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(userRole));
                UserRepresentation createdUser = keycloak.realm(realm).users().get(userId).toRepresentation();

                SystemUser sUser = SystemUser.builder()
                        .userId(userId)
                        .keycloakId(createdUser.getId())
                        .firstName(userRequestDto.getFirstName())
                        .lastName(userRequestDto.getLastName())
                        .email(userRequestDto.getEmail())
                        .contact(userRequestDto.getContact())
                        .isActive(false)
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(false)
                        .isEmailVerified(false)
                        .createdAt(new Date().toInstant())
                        .updatedAt(new Date().toInstant())
                        .build();

                SystemUser savedUser = systemUserRepo.save(sUser);
                Otp createdOtp = Otp.builder()
                        .propertyId(UUID.randomUUID().toString())
                        .code(otpGenerator.generateOtp(5))
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .isVerified(false)
                        .systemUser(savedUser)
                        .attempts(0)
                        .build();
                otpRepo.save(createdOtp);
                emailService.sendUserSignupVerificationCode(userRequestDto.getEmail(), "Verify your email", createdOtp.getCode(), userRequestDto.getFirstName());
            }

        }

        private UserRepresentation mapUserRepo(SystemUserRequestDto dto, boolean isEmailVerified, boolean isEnabled) {
            UserRepresentation user = new UserRepresentation();
            user.setEmail(dto.getEmail());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setUsername(dto.getEmail());
            user.setEnabled(isEnabled);
            user.setEmailVerified(isEmailVerified);
            List<CredentialRepresentation> credList = new ArrayList<>();
            CredentialRepresentation cred = new CredentialRepresentation();
            cred.setTemporary(false);
            cred.setValue(dto.getPassword());
            credList.add(cred);
            user.setCredentials(credList);
            return user;
        }

    }

