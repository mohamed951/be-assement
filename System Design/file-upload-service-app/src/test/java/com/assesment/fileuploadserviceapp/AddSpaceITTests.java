package com.assesment.fileuploadserviceapp;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddSpaceITTests extends AbstractApplicationTests {

    @Test
    void givenNoThing_whenPostSpaceWithoutAuthorizationHeader_thenReturnUnAuthorized() throws Exception {
        mockMvc.perform(post("/spaces")
                        .content("spaceX"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void givenNoThing_whenPostSpaceWithInvalidName_thenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/spaces")
                        .content("")
                        .header(HttpHeaders.AUTHORIZATION, "viewer@test.com"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenNoThing_whenPostSpaceWithViewerPrivilege_thenReturnForbidden() throws Exception {
        mockMvc.perform(post("/spaces")
                        .content("spaceX")
                        .header(HttpHeaders.AUTHORIZATION, "viewer@test.com"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DatabaseSetup(value = "/dbUnit/users_permissions.xml", type = DatabaseOperation.CLEAN_INSERT)
    void givenNoThing_whenPostSpaceWithAdminPrivilege_thenReturnOk() throws Exception {
        mockMvc.perform(post("/spaces")
                        .content("spaceX")
                        .header(HttpHeaders.AUTHORIZATION, "admin@test.com"))
                .andExpect(status().isOk());
    }
}
