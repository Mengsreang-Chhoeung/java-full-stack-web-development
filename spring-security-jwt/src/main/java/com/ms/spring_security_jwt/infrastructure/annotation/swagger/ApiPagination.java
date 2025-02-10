package com.ms.spring_security_jwt.infrastructure.annotation.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Parameters(value = {
        @Parameter(name = "isPaged", example = "true", description = "Specify the data is `page` or `list`", schema = @Schema(type = "boolean")),
        @Parameter(name = "page", example = "0", description = "Specify the `page of data` to show", schema = @Schema(type = "number", minimum = "0")),
        @Parameter(name = "size", example = "20", description = "Specify the `size of data` to show", schema = @Schema(type = "number", minimum = "1")),
        @Parameter(name = "q", description = "Specify the `q` for search data example: `name:abc` for single search and `name:abc;sex:male`", schema = @Schema(type = "string")),
        @Parameter(name = "sort", description = "Specify the sort for sort data example: `id:desc` for single sort and `id:desc,name:asc` for multiple sorts", schema = @Schema(type = "string", pattern = "^\\w+:(asc|desc)(,\\w+:(asc|desc))*$"))
})
public @interface ApiPagination {
}
