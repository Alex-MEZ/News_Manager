package com.newsmanager.security;

import com.newsmanager.controller.SecuredController;
import com.newsmanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SecuredController.class)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAccessToSecuredEndpointAsAdmin() throws Exception {
        mockMvc.perform(get("/api/secured-endpoint"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAccessToSecuredEndpointAsUser() throws Exception {
        mockMvc.perform(get("/api/secured-endpoint"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/api/secured-endpoint"))
                .andExpect(status().isUnauthorized());
    }

}
