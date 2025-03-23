package com.titov.feign_auth_client;

import com.titov.feign_auth_client.config.DestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequiredArgsConstructor
@Slf4j
public class PullController {

//    private final DestClient destClient;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

//    @GetMapping("pull/{val}")
//    public String getDestination(@PathVariable String val) {
//        log.info(this.clientRegistrationRepository.findByRegistrationId("keycloak").getClientId());
//        authorizedClientService.loadAuthorizedClient(this.clientRegistrationRepository
//                .findByRegistrationId("keycloak")
//                        .getRegistrationId()
//                ,
//
//        )

//        log.info("pull/{val}");
//        return destClient.getThrough(val);
//    }
}
