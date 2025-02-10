package com.ms.spring_security_jwt.constant;

import com.ms.spring_security_jwt.util.StaticBeanUtil;

public final class AppConstant {
    public static final String APP_NAME = StaticBeanUtil.getAppProperty().getAppName();
    public static final String APP_DESCRIPTION = StaticBeanUtil.getAppProperty().getAppDescription();
    public static final String APP_VERSION = StaticBeanUtil.getAppProperty().getAppVersion();
    public static final String APP_ORIGIN_URL = StaticBeanUtil.getAppProperty().getAppOriginUrl();

    // JWT
    public static final String JWT_SCHEME_NAME = "bearerAuth";
    public static final String JWT_BEARER_FORMAT = "JWT";
    public static final String JWT_SCHEME = "bearer";

    // MEDIA TYPE
    public static final String APPLICATION_JSON = "application/json";
}
