/*
 * Author: Oleksii Hnidan
 * Project: web-security26 | Lab: 8 | Class: AuthenticationService
 */
package chnu.edu.websecurity26.auth;

import chnu.edu.websecurity26.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(), request.password()));
        UserDetails user = userDetailsService.loadUserByUsername(request.username());
        return new AuthenticationResponse(jwtService.generateToken(user));
    }
}
