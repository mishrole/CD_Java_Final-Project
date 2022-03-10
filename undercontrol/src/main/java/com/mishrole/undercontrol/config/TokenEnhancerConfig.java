package com.mishrole.undercontrol.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.service.UserService;

@Component
public class TokenEnhancerConfig implements TokenEnhancer {
	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = userService.findUserByEmail(authentication.getName());
		
		Map<String, Object> information = new HashMap<>();
		//information.put("name", user.getLastname() + ' ' + user.getFirstname());
		information.put("firstname", user.getFirstname());
		information.put("lastname", user.getLastname());
		information.put("email", user.getEmail());
		information.put("roles", user.getRoles());
		// Can't pass user, error Handling error: IllegalStateException, Cannot convert access token to JSON
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);
		return accessToken;
		
	}
}
