package com.example.smsui.controller;

import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.SecurityContextAccessor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;


public class AccessTokenConfig {

    public static String getToken(){

        //we can create this method in controller but if we redirect to some other endpoint then we need to define again

        OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder
                .getContextHolderStrategy().getContext()
                .getAuthentication().getDetails();

        return auth2AuthenticationDetails.getTokenType()
                .concat(" ")
                .concat(auth2AuthenticationDetails.getTokenValue());

    }
}
