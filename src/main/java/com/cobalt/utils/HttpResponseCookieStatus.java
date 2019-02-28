package com.cobalt.utils;

import lombok.NonNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public interface HttpResponseCookieStatus {
    String cookieName = "__status";
    String cookieSucces = "SUCCESS";
    String cookieFailed = "FAILED";
    Integer cookieMaxAge = -1; //cookie is not stored

    default void addStatus(@NonNull final HttpServletResponse response,
                           @NonNull final String cookieName,
                           @NonNull final String status,
                           @NonNull final String path,
                           @NonNull final Integer maxAge) {
        final Cookie cookie = new Cookie(cookieName, status);
        {
            cookie.setPath(path);
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    default void addSuccessCookieStatus(@NonNull final HttpServletResponse response,
                                        @NonNull final String path) {
        addStatus(response, cookieName, cookieSucces, path, cookieMaxAge);
    }

    default void addFailedCookieStatus(@NonNull final HttpServletResponse response,
                                       @NonNull final String path) {
        addStatus(response, cookieName, cookieFailed, path, cookieMaxAge);
    }
}
