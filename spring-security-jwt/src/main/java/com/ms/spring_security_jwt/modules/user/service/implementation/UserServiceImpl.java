package com.ms.spring_security_jwt.modules.user.service.implementation;

import com.ms.spring_security_jwt.constant.enums.UserRoleEnum;
import com.ms.spring_security_jwt.infrastructure.exception.BadRequestException;
import com.ms.spring_security_jwt.infrastructure.exception.ConflictException;
import com.ms.spring_security_jwt.infrastructure.exception.NotFoundException;
import com.ms.spring_security_jwt.infrastructure.model.request.BaseRequest;
import com.ms.spring_security_jwt.infrastructure.repository.BaseRepository;
import com.ms.spring_security_jwt.infrastructure.service.UrlParamService;
import com.ms.spring_security_jwt.infrastructure.service.implementation.BaseCrudServiceImpl;
import com.ms.spring_security_jwt.modules.security.model.UserAuthDetails;
import com.ms.spring_security_jwt.modules.security.service.UserAuthDetailsService;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import com.ms.spring_security_jwt.modules.user.model.request.BackendCreateUserRequest;
import com.ms.spring_security_jwt.modules.user.model.request.BackendUpdateUserRequest;
import com.ms.spring_security_jwt.modules.user.repository.UserRepository;
import com.ms.spring_security_jwt.modules.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseCrudServiceImpl<UserEntity, Long> implements UserService {
    private final UserRepository userRepository;
    private final UserAuthDetailsService userAuthDetailsService;

    @Override
    public BaseRepository<UserEntity, Long> getRepository() {
        return this.userRepository;
    }

    @Override
    public UserEntity create(BaseRequest<UserEntity> req) {
        // 1. casting the request
        BackendCreateUserRequest userRequest = (BackendCreateUserRequest) req;

        // 2. check username exist or not
        if (isUsernameExist(userRequest.getUsername()))
            throw new ConflictException("Username already taken!");

        // 3. check email exist or not
        if (isEmailExist(userRequest.getEmail()))
            throw new ConflictException("Email already taken!");

        // 4. checking that if current user's role is ADMIN, so cannot create another admin user.
        // Meaning that ADMIN user can create only CUSTOMER user.
        UserAuthDetails currentUser = this.userAuthDetailsService.getCurrentUser();
        if (currentUser.getRole() == UserRoleEnum.ADMIN)
            if (userRequest.getRole() == UserRoleEnum.ADMIN)
                throw new BadRequestException("Creation failed!");

        // 5. call super class to execute the existing functionalities
        return super.create(userRequest);
    }

    @Override
    public UserEntity update(Long id, BaseRequest<UserEntity> req) {
        // 1. casting the request
        BackendUpdateUserRequest userRequest = (BackendUpdateUserRequest) req;

        // 2. check the id that has provided exist in our db or not
        UserEntity foundUser = this.userRepository.findOneAvailable(id).orElseThrow(() -> new NotFoundException("User not found!"));

        // 3. cannot update yourself
        UserAuthDetails currentUser = this.userAuthDetailsService.getCurrentUser();
        if (Objects.equals(currentUser.getId(), id))
            throw new BadRequestException("Cannot update yourself!");

        // 4. check conditions for super admin user
        // that can update only customers user and admins user
        // and cannot update another super admins user
        if (currentUser.getRole() == UserRoleEnum.SUPER_ADMIN) {
            if (foundUser.getRole() != UserRoleEnum.CUSTOMER && foundUser.getRole() != UserRoleEnum.ADMIN)
                throw new BadRequestException("Cannot update this user!");
        }

        // 5. check conditions for admin user
        // that can update only customers user
        // and cannot update another admins user
        // and cannot update super admins user
        if (currentUser.getRole() == UserRoleEnum.ADMIN) {
            if (foundUser.getRole() != UserRoleEnum.CUSTOMER)
                throw new BadRequestException("Cannot update this user!");
        }

        // 6. check username exist or not
        if (!Objects.equals(foundUser.getUsername(), userRequest.getUsername()))
            if (isUsernameExist(userRequest.getUsername()))
                throw new ConflictException("Username already taken!");

        // 7. check email exist or not
        if (!Objects.equals(foundUser.getEmail(), userRequest.getEmail()))
            if (isEmailExist(userRequest.getEmail()))
                throw new ConflictException("Email already taken!");

        // 8. setting existing data to the request object
        userRequest.setPassword(foundUser.getPassword());
        userRequest.setExistingEntity(foundUser);

        // 9. call super class to execute the existing functionalities
        return super.update(id, userRequest);
    }

    @Override
    public void softDelete(Long id) {
        // 1. check the id that has provided exist in our db or not
        UserEntity foundUser = this.userRepository.findOneAvailable(id).orElseThrow(() -> new NotFoundException("User not found!"));

        // 2. cannot delete yourself
        UserAuthDetails currentUser = this.userAuthDetailsService.getCurrentUser();
        if (Objects.equals(currentUser.getId(), id))
            throw new BadRequestException("Cannot delete yourself!");

        // 3. check conditions for super admin user
        // that can delete only customers user and admins user
        // and cannot delete another super admins user
        if (currentUser.getRole() == UserRoleEnum.SUPER_ADMIN) {
            if (foundUser.getRole() != UserRoleEnum.CUSTOMER && foundUser.getRole() != UserRoleEnum.ADMIN)
                throw new BadRequestException("Cannot delete this user!");
        }

        // 4. check conditions for admin user
        // that can delete only customers user
        // and cannot delete another admins user
        // and cannot delete super admins user
        if (currentUser.getRole() == UserRoleEnum.ADMIN) {
            if (foundUser.getRole() != UserRoleEnum.CUSTOMER)
                throw new BadRequestException("Cannot delete this user!");
        }

        // 5. do a soft delete
        super.softDelete(foundUser);
    }

    @Override
    public Optional<UserEntity> findOneAvailable(Long id) {
        // 1. Get the record
        Optional<UserEntity> foundUser = super.findOneAvailable(id);
        if (foundUser.isEmpty()) throw new NotFoundException("User not found!");

        // 2. Get current user info
        UserAuthDetails currentUser = this.userAuthDetailsService.getCurrentUser();

        // 3. Cannot get the current user info
        if (Objects.equals(currentUser.getId(), foundUser.get().getId()))
            throw new BadRequestException("Invalid user!");

        // 4. check conditions for super admin user
        // that can get only customers user and admins user
        // and cannot get another super admins user
        if (currentUser.getRole() == UserRoleEnum.SUPER_ADMIN) {
            if (foundUser.get().getRole() != UserRoleEnum.CUSTOMER && foundUser.get().getRole() != UserRoleEnum.ADMIN)
                throw new BadRequestException("Invalid user!");
        }

        // 5. check conditions for admin user
        // that can get only customers user
        // and cannot get another admins user
        if (currentUser.getRole() == UserRoleEnum.ADMIN) {
            if (foundUser.get().getRole() != UserRoleEnum.CUSTOMER)
                throw new BadRequestException("Invalid user!");
        }

        return foundUser;
    }

    @Override
    public Page<UserEntity> findAllAvailable(UrlParamService param) {
        // 1. Get current user info
        UserAuthDetails currentUser = this.userAuthDetailsService.getCurrentUser();

        // 2. check conditions for super admin user
        if (currentUser.getRole() == UserRoleEnum.SUPER_ADMIN) {
            param.setInternalSearch("role:ADMIN;role:CUSTOMER");
        }

        // 3. check conditions for admin user
        if (currentUser.getRole() == UserRoleEnum.ADMIN) {
            param.setInternalSearch("role:CUSTOMER");
        }

        // 4. setting allowed search fields
        Set<String> allowedSearchFields = new HashSet<>();
        allowedSearchFields.add("username");
        allowedSearchFields.add("email");
        param.setAllowedSearchFields(allowedSearchFields);

        // 5. return data
        return super.findAllAvailable(param);
    }

    private Boolean isEmailExist(String email) {
        return this.userRepository.existByFieldAvailable("email", email);
    }

    private Boolean isUsernameExist(String username) {
        return this.userRepository.existByFieldAvailable("username", username);
    }
}
