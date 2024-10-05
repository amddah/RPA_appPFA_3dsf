package com.example.rpa.Config;

import com.example.rpa.Dtos.UserInfo;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final WebClient userInfoClient;
     public GoogleOpaqueTokenIntrospector(WebClient userInfoClient){

         this.userInfoClient=userInfoClient;
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {

        UserInfo user = userInfoClient.get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")
                .header("Authorization", "Bearer " + token) // Use Authorization header
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();


        Map<String,Object> attributs =new HashMap<>();

        attributs.put("sub",user.sub());
        attributs.put("name",user.name());

        return new DefaultOAuth2AuthenticatedPrincipal(user.name(),attributs,null);
    }
}
