/*
 * Author: Oleksii Hnidan
 * Project: web-security26 | Lab: 7 | Class: AccessTests
 */
package chnu.edu.websecurity26.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccessTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void anonymousCannotListTracks() throws Exception {
        mockMvc.perform(get("/api/v1/tracks"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void authenticatedUserCanListTracks() throws Exception {
        mockMvc.perform(get("/api/v1/tracks").with(user("listener")))
                .andExpect(status().isOk());
    }

    @Test
    void adminCanUseAdminRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/admin").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    void userCannotUseAdminRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/admin").with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    void userCanUseUserRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/user").with(user("user").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void adminCannotUseUserRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/user").with(user("admin").roles("ADMIN")))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCanUseMemberRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/member").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    void userCanUseMemberRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/member").with(user("user").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void superadminCanUseSuperadminRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/superadmin")
                        .with(user("superadmin").roles("SUPERADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    void adminCannotUseSuperadminRoute() throws Exception {
        mockMvc.perform(get("/api/v1/tracks/hello/superadmin").with(user("admin").roles("ADMIN")))
                .andExpect(status().isForbidden());
    }
}
