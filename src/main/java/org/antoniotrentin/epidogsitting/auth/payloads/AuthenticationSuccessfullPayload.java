package org.antoniotrentin.epidogsitting.auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationSuccessfullPayload {
    private String accessToken;
}