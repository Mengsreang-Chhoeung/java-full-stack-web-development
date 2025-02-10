package com.ms.spring_security_jwt.constant;

public final class ResponseMessageConstant {
    public static final String FIND_ALL_SUCCESSFULLY = "Fetch all records successfully";
    public static final String FIND_ONE_SUCCESSFULLY = "Fetch a record successfully";
    public static final String UPDATE_SUCCESSFULLY = "Updated successfully";
    public static final String DELETE_SUCCESSFULLY = "Deleted successfully";
    public static final String RESTORE_SUCCESSFULLY = "Restored successfully";
    public static final String CREATE_SUCCESSFULLY = "Created successfully";
    public static final String CANCEL_SUCCESSFULLY = "Cancelled successfully";
    public static final String EXECUTE_SUCCESSFULLY = "Executed successfully";

    public static final String INCORRECT_USER_PASSWORD = "Incorrect user or password!";
    public static final String INVALID_PASSWORD = "Password is not valid";
    public static final String LOGIN_SUCCESS = "Login successfully!";
    public static final String LOGOUT_SUCCESS = "Logout successfully!";
    public static final String USER_NOT_VERIFIED = "User is not verified!";
    public static final String REGISTER_SUCCESS = "Register successfully!";
    public static final String FETCH_ME_SUCCESS = "Fetch me successfully!";
    public static final String REQUEST_RESET_PASSWORD_SUCCESS = "You has been request to reset password!";
    public static final String REQUEST_VERIFY_USER_SUCCESS = "You has been request to verify user!";
    public static final String REQUEST_REFRESH_TOKEN_SUCCESS = "You has been request to refresh token!";
    public static final String PASSWORD_CHANGED_SUCCESS = "Change password successfully!";

    public static final String OPERATION_SUCCESSFULLY = "Success";
    public static final String OPERATION_UNAUTHORIZED = "Unauthorized";
    public static final String OPERATION_CONFLICT = "Conflict";
    public static final String OPERATION_NOT_FOUND = "Not Found";
    public static final String OPERATION_FORBIDDEN = "Forbidden";
    public static final String OPERATION_BAD_REQUEST = "Bad Request";
    public static final String OPERATION_INTERNAL_SERVER_ERROR = "Internal Server Error";

    public static final String ERROR_OPERATION = "400 - " + OPERATION_BAD_REQUEST +
            ", 401 - " + OPERATION_UNAUTHORIZED +
            ", 403 - " + OPERATION_FORBIDDEN +
            ", 404 - " + OPERATION_NOT_FOUND +
            ", 409 - " + OPERATION_CONFLICT +
            ", 500 - " + OPERATION_INTERNAL_SERVER_ERROR;

}
