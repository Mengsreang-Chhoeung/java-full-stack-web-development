package com.ms.spring_security_jwt.constant;

public final class RestURIConstant {
    public static final String INDEX = "/api";
    public static final String ERROR = "/error";
    public static final String TEST = "/test";

    // Backend
    public static final String B_INDEX = INDEX + "/backend";
    public static final String B_AUTH = B_INDEX + "/auth";
    public static final String B_USER = B_INDEX + "/user";
    public static final String B_CATEGORY = B_INDEX + "/category";
    public static final String B_ATTACHMENT = B_INDEX + "/attachment";
    public static final String B_SLIDER = B_INDEX + "/slider";
    public static final String B_CONFIGURATION = B_INDEX + "/configuration";

    // Frontend
    public static final String F_INDEX = INDEX + "/frontend";
    public static final String F_AUTH = F_INDEX + "/auth";

    // Other
    public static final String O_FILE = "/files";
}
