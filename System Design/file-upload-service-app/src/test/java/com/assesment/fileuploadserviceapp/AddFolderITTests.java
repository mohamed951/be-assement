package com.assesment.fileuploadserviceapp;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddFolderITTests extends AbstractApplicationTests {

    @Test
    void givenNoThing_whenPostFolderWithoutAuthorizationHeader_thenReturnUnAuthorized() throws Exception {
        mockMvc.perform(post("/spaces/101/folders"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void givenNoThing_whenPostFolderWithInvalidName_thenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/spaces/101/folders")
                        .content("")
                        .header(HttpHeaders.AUTHORIZATION, "viewer@test.com"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenNoThing_whenPostFolderToNonExistingSpace_thenReturnNotFound() throws Exception {
        mockMvc.perform(post("/spaces/102/folders")
                        .content("new-folder")
                        .header(HttpHeaders.AUTHORIZATION, "viewer@test.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DatabaseSetup(value = "/dbUnit/space_with_user_permission.xml", type = DatabaseOperation.CLEAN_INSERT)
    void givenSpace_whenPostFolderToFolderWithViewPrivilege_thenReturnForbidden() throws Exception {
        mockMvc.perform(post("/spaces/101/folders")
                        .content("new-folder")
                        .header(HttpHeaders.AUTHORIZATION, "viewer@test.com"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DatabaseSetup(value = "/dbUnit/space_with_user_permission.xml", type = DatabaseOperation.CLEAN_INSERT)
    void givenSpace_whenPostFolderToFolderWithEditPrivilege_thenReturnSuccess() throws Exception {
        mockMvc.perform(post("/spaces/101/folders")
                        .content("new-folder")
                        .header(HttpHeaders.AUTHORIZATION, "admin@test.com"))
                .andExpect(status().isOk());
    }
}
