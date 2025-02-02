package com.ehtesham.hospitalmanagement.services;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/v0/users/**";
    public static final String H2_URL = "/h2/console/**";
    public static final String SECRET_KEY = "SecretKeyToGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3000000; /* 1 second = 1,000 */

}