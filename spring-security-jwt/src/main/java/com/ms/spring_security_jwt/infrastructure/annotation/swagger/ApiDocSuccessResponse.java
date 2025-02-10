package com.ms.spring_security_jwt.infrastructure.annotation.swagger;

import com.ms.spring_security_jwt.constant.AppConstant;
import com.ms.spring_security_jwt.constant.ResponseMessageConstant;
import com.ms.spring_security_jwt.infrastructure.model.response.api_docs.success.SuccessResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "200", description = ResponseMessageConstant.OPERATION_SUCCESSFULLY, content = {
        @Content(mediaType = AppConstant.APPLICATION_JSON, schema = @Schema(implementation = SuccessResponse.class))
})
public @interface ApiDocSuccessResponse {
}
