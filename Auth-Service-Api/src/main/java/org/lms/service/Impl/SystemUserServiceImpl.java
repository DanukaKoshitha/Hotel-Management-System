package org.lms.service.Impl;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.lms.config.KeycloakSecurityUtil;
import org.lms.dto.request.PasswordRequestDto;
import org.lms.dto.request.SystemUserRequestDto;
import org.lms.entity.Otp;
import org.lms.entity.SystemUser;
import org.lms.exception.BadRequestException;
import org.lms.exception.DuplicateEntryException;
import org.lms.exception.EntryNotFoundException;
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
        if (userRequestDto.getFirstName() == null || userRequestDto.getFirstName().trim().isEmpty()) {
            throw new BadRequestException("First name is required");
        }

        if (userRequestDto.getLastName() == null || userRequestDto.getLastName().trim().isEmpty()) {
            throw new BadRequestException("Last name is required");
        }

        if (userRequestDto.getEmail() == null || userRequestDto.getEmail().trim().isEmpty()) {
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

            //email send
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

    @Override
    public void initializeHosts(List<SystemUserRequestDto> users) throws IOException {
        for(SystemUserRequestDto dto: users){
            Optional<SystemUser> selectedUser = systemUserRepo.findByEmail(dto.getEmail());

            if(selectedUser.isPresent()){
                continue;
            }


            String userId = "";
            String otp = "";
            Keycloak keycloak = null;

            UserRepresentation existingUser = null;
            keycloak = securityUtil.getKeycloak();

            existingUser = keycloak.realm(realm).users().search(dto.getEmail()).stream()
                    .findFirst().orElse(null);

            if (existingUser != null) {
                Optional<SystemUser> selectedSystemUserFromAuthService =
                        systemUserRepo.findByEmail(dto.getEmail());
                if (selectedSystemUserFromAuthService.isEmpty()) {
                    keycloak.realm(realm).users().delete(existingUser.getId());
                } else {
                    throw new DuplicateEntryException("Email already exists");
                }
            } else {
                Optional<SystemUser> selectedSystemUserFromAuthService =
                        systemUserRepo.findByEmail(dto.getEmail());
                if (selectedSystemUserFromAuthService.isPresent()) {
                    Optional<Otp> selectedOtp =
                            otpRepo.findBySystemUserId(selectedSystemUserFromAuthService.get().getUserId());
                    if (selectedOtp.isPresent()) {
                        otpRepo.deleteById(selectedOtp.get().getPropertyId());
                    }
                    systemUserRepo.deleteById(selectedSystemUserFromAuthService.get().getUserId());
                }
            }

            UserRepresentation userRepresentation = mapUserRepo(dto, true, true);
            Response response = keycloak.realm(realm).users().create(userRepresentation);
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                RoleRepresentation userRole = keycloak.realm(realm).roles().get("host").toRepresentation();
                userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(userRole));
                UserRepresentation createdUser = keycloak.realm(realm).users().get(userId).toRepresentation();

                SystemUser sUser = SystemUser.builder()
                        .userId(userId)
                        .keycloakId(createdUser.getId())
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .email(dto.getEmail())
                        .contact(dto.getContact())
                        .isActive(true)
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .isEmailVerified(true)
                        .createdAt(new Date().toInstant())
                        .updatedAt(new Date().toInstant())
                        .build();

                SystemUser savedUser = systemUserRepo.save(sUser);
                emailService.sendHostPassword(dto.getEmail(), "access system by using the above password", dto.getPassword(), dto.getFirstName());
            }
        }
    }

    @Override
    public void resend(String email, String type) {
        try{
            Optional<SystemUser> selectedUser = systemUserRepo.findByEmail(email);
            if(selectedUser.isEmpty()){
                throw new EntryNotFoundException("unable to find any users associated with the provided email address");
            }

            SystemUser systemUser = selectedUser.get();

            if(type.equalsIgnoreCase("SIGNUP")){
                if(systemUser.isEmailVerified()){
                    throw new DuplicateEntryException("The email is already activated");
                }
            }

            Otp selectedOtpObj = systemUser.getOtp();
            String code = otpGenerator.generateOtp(5);

            emailService.sendUserSignupVerificationCode(systemUser.getEmail(), "verify your email", code, systemUser.getFirstName());

            selectedOtpObj.setAttempts(0);
            selectedOtpObj.setCode(code);
            selectedOtpObj.setVerified(false);
            selectedOtpObj.setUpdatedAt(new Date().toInstant());
            otpRepo.save(selectedOtpObj);

        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void forgotPasswordSendVerificationCode(String email) {
        try{
            Optional<SystemUser> selectedUser = systemUserRepo.findByEmail(email);
            if(selectedUser.isEmpty()){
                throw new EntryNotFoundException("unable to find any users associated with the provided email address");
            }

            SystemUser systemUser = selectedUser.get();

            Keycloak keycloak =null;
            keycloak = securityUtil.getKeycloak();
            UserRepresentation existingUser =
                    keycloak.realm(realm).users().search(email).stream().findFirst().orElse(null);

            if(existingUser==null){
                throw new EntryNotFoundException("unable to find any users associated with the provided email address");
            }


            Otp selectedOtpObj = systemUser.getOtp();
            String code = otpGenerator.generateOtp(5);


            selectedOtpObj.setAttempts(0);
            selectedOtpObj.setCode(code);
            selectedOtpObj.setVerified(false);
            selectedOtpObj.setUpdatedAt(new Date().toInstant());
            otpRepo.save(selectedOtpObj);

            emailService.sendUserSignupVerificationCode(systemUser.getEmail(), "verify your email to reset the password", code, systemUser.getFirstName());
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean verifyReset(String otp, String email) {
        try{
            Optional<SystemUser> selectedUser = systemUserRepo.findByEmail(email);
            if(selectedUser.isEmpty()){
                throw new EntryNotFoundException("unable to find any users associated with the provided email address");
            }

            SystemUser systemUserOb = selectedUser.get();
            Otp otpOb = systemUserOb.getOtp();

            if(otpOb.getCode().equals(otp)){
                //otpRepo.deleteById(otpOb.getPropertyId());
                otpOb.setAttempts(otpOb.getAttempts()+1);
                otpOb.setUpdatedAt(new Date().toInstant());
                otpOb.setVerified(true);
                otpRepo.save(otpOb);
                return true;
            }else{

                if (otpOb.getAttempts()>=5) {
                    resend(email, "PASSWORD");
                    throw new BadRequestException("you have a new verification code");
                }

                otpOb.setAttempts(otpOb.getAttempts()+1);
                otpOb.setUpdatedAt(new Date().toInstant());
                otpRepo.save(otpOb);
                return false;
            }

        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean passwordReset(PasswordRequestDto dto) {
        Optional<SystemUser> selectedUserObj = systemUserRepo.findByEmail(dto.getEmail());
        if(selectedUserObj.isPresent()){

            SystemUser systemUser = selectedUserObj.get();
            Otp otpObj = systemUser.getOtp();
            Keycloak keycloak = securityUtil.getKeycloak();
            List<UserRepresentation> keyCloakUsers = keycloak.realm(realm).users().search(systemUser.getEmail());

            if(!keyCloakUsers.isEmpty() && otpObj.getCode().equals(dto.getCode())){
                UserRepresentation keyCloakUser = keyCloakUsers.get(0);
                UserResource userResource = keycloak.realm(realm).users().get(keyCloakUser.getId());
                CredentialRepresentation newPass = new CredentialRepresentation();
                newPass.setType(CredentialRepresentation.PASSWORD);
                newPass.setValue(dto.getPassword());
                newPass.setTemporary(false);
                userResource.resetPassword(newPass);

                systemUser.setUpdatedAt(new Date().toInstant());
                systemUserRepo.save(systemUser);

                return true;
            }
            throw new BadRequestException("try again");
        }
        throw new EntryNotFoundException("unable to find!");
    }
}



