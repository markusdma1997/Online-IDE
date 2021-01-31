package edu.tum.ase.authentication.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@EnableOAuth2Sso
@RestController
public class Controller {
    @RequestMapping("/user")
    public Principal user() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/authenticated")
    public boolean authenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ANONYMOUS"));
        }
        return false;
    }

    @GetMapping("/getUsername")
    public String getUsername() {
        String username = "";
        String gitlabUserSearchUri = ymlConfig.getUserInfoUri();
        System.out.println("gitlabUserSearchUri is " + gitlabUserSearchUri);
        JsonNode userJsonNode = restTemplate.getForObject(gitlabUserSearchUri, JsonNode.class);
        if (!userJsonNode.isNull()) {
            username = userJsonNode.get("name").asText();
            System.out.println("in the for loop, username is " + username);
        }
        return username;
    }
}
