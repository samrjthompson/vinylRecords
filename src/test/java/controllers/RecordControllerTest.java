package controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.vinylrecords.controllers.RecordController;
import com.vinylrecords.models.RecordDocument;
import com.vinylrecords.services.RecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RecordController.class)
@ContextConfiguration(classes = RecordController.class)
class RecordControllerTest {
    private static final String ALL_RECORDS_URL = "/records";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RecordDocument document;

    @MockBean
    private RecordService service;

    @InjectMocks
    private RecordController controller;

    @Test
    @DisplayName("Returns 200 OK when GET request sent to endpoint")
    void testSuccessfulGetAllRecords() throws Exception {
        when(service.getAllRecords()).thenReturn(Optional.of(new ArrayList<RecordDocument>()));

        mockMvc.perform(get(ALL_RECORDS_URL)
                        .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Returns 404 Not found when GET request sent to endpoint and no documents exist")
    void testNotFoundGetAllRecords() throws Exception {
        when(service.getAllRecords()).thenReturn(Optional.empty());

        mockMvc.perform(get(ALL_RECORDS_URL)
                        .contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound());
    }
}
