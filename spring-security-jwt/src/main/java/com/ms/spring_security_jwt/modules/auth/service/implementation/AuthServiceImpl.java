package com.ms.spring_security_jwt.modules.auth.service.implementation;

import com.ms.spring_security_jwt.constant.enums.UserRoleEnum;
import com.ms.spring_security_jwt.infrastructure.exception.BadRequestException;
import com.ms.spring_security_jwt.infrastructure.exception.NotFoundException;
import com.ms.spring_security_jwt.modules.auth.model.request.BackendAuthLoginRequest;
import com.ms.spring_security_jwt.modules.auth.model.request.FrontendAuthLoginRequest;
import com.ms.spring_security_jwt.modules.auth.model.response.AuthTokenResponse;
import com.ms.spring_security_jwt.modules.auth.service.AuthService;
import com.ms.spring_security_jwt.modules.security.model.UserAuthDetails;
import com.ms.spring_security_jwt.modules.security.service.UserAuthDetailsService;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import com.ms.spring_security_jwt.modules.user.model.response.UserResponse;
import com.ms.spring_security_jwt.modules.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserAuthDetailsService userAuthDetailsService;

    private Optional<Tuple> getUserByEmail(String email) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        criteriaQuery.multiselect(
                        root.get("id").alias("id"),
                        root.get("username").alias("username"),
                        root.get("password").alias("password"),
                        root.get("role").alias("role")
                )
                .where(builder.equal(root.get("email"), email), builder.isNull(root.get("deletedAt")));

        TypedQuery<Tuple> query = this.entityManager.createQuery(criteriaQuery);

        List<Tuple> result = query.getResultList();
        if (result.size() > 0) return Optional.of(result.get(0));
        else return Optional.empty();
    }

    private Optional<Tuple> getUserByUsername(String username) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        criteriaQuery.multiselect(
                        root.get("id").alias("id"),
                        root.get("username").alias("username"),
                        root.get("password").alias("password"),
                        root.get("role").alias("role")
                )
                .where(builder.equal(root.get("username"), username), builder.isNull(root.get("deletedAt")));

        TypedQuery<Tuple> query = this.entityManager.createQuery(criteriaQuery);

        List<Tuple> result = query.getResultList();
        if (result.size() > 0) return Optional.of(result.get(0));
        else return Optional.empty();
    }

    @Override
    public AuthTokenResponse backendLogin(BackendAuthLoginRequest request) {
        // 1. Need to find the existing user in the db by using the username that get from the request
        Tuple data = this.getUserByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("User not found!"));

        // 2. If the record is existing, then let's check with the role of the user
        if (data.get("role", UserRoleEnum.class) != UserRoleEnum.ADMIN && data.get("role", UserRoleEnum.class) != UserRoleEnum.SUPER_ADMIN)
            throw new BadRequestException("Invalid user!");

        // 3. If the record is existing, then let's check with requested password and the hashing password from db
        if (!this.passwordEncoder.matches(request.getPassword(), data.get("password", String.class)))
            throw new BadRequestException("Invalid password!");

        // 4. return data
        return AuthTokenResponse.toResponse(data);
    }

    @Override
    public AuthTokenResponse frontendLogin(FrontendAuthLoginRequest request) {
        // 1. Need to find the existing user in the db by using the email that get from the request
        Tuple data = this.getUserByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("User not found!"));

        // 2. If the record is existing, then let's check with the role of the user
        if (data.get("role", UserRoleEnum.class) != UserRoleEnum.CUSTOMER)
            throw new BadRequestException("Invalid user!");

        // 3. If the record is existing, then let's check with requested password and the hashing password from db
        if (!this.passwordEncoder.matches(request.getPassword(), data.get("password", String.class)))
            throw new BadRequestException("Invalid password!");

        // 4. return data
        return AuthTokenResponse.toResponse(data);
    }

    @Override
    public UserResponse ownInfo() {
        // 1. Getting current user
        UserAuthDetails userAuthDetails = this.userAuthDetailsService.getCurrentUser();

        // 3. Fetching full user information from db
        UserEntity foundOwnInfo = this.userRepository.findOneAvailable(userAuthDetails.getId()).orElseThrow(() -> new BadRequestException("Invalid credential!"));

        return UserResponse.toResponse(foundOwnInfo);
    }
}
