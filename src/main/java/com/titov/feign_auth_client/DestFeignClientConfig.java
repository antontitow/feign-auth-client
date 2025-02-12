package com.titov.feign_auth_client;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import static java.util.Objects.isNull;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class DestFeignClientConfig {

    public static final String CLIENT_REGISTRATION_ID = "keycloak";

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public RequestInterceptor requestInterceptor(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager,
                                                 ClientRegistrationRepository clientRegistrationRepository) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(CLIENT_REGISTRATION_ID);
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + getAccessToken(oAuth2AuthorizedClientManager,
                    clientRegistration));
        };
    }

    public String getAccessToken(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager,
                                 ClientRegistration clientRegistration) {
        try {
            OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId(clientRegistration.getRegistrationId())
                    .principal("keycloak")
                    .build();
            OAuth2AuthorizedClient client = oAuth2AuthorizedClientManager.authorize(oAuth2AuthorizeRequest);
            if (isNull(client)) {
                throw new IllegalStateException("client credentials flow on " + clientRegistration.getRegistrationId() + " failed, client is null");
            }
            return client.getAccessToken().getTokenValue();
        } catch (Exception exp) {
            log.error("client credentials error " + exp.getMessage());
        }
        return null;
    }
}
