package com.epam.brest;

import com.epam.brest.model.entity.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.epam.brest.model.entity.constants.RequestConstants.GROUPE_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-rest-test.xml"})
public class RequestServiceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceRestTest.class);

    public static final String REQUESTS_URL = "http://localhost:8099/requests";

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    RequestServiceRest requestService;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        requestService = new RequestServiceRest(REQUESTS_URL, restTemplate);
    }

    @Test
    public void shouldFindAllusers() throws Exception {
        LOGGER.debug("shouldFindAllUsers()");
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(REQUESTS_URL + "/" + 1)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(1, 1), create(1, 2))))
                );
        // when
        List<Request> requests = requestService.getAllRequestsByIdService(1);
        // then
        mockServer.verify();
        assertNotNull(requests);
        assertTrue(requests.size() > 0);
    }

    @Test
    public void shouldCreateNewRequest() throws Exception {
        LOGGER.debug("shouldCreateRequest()");
        // given
        Request request = new Request();
        request.setGroupe(RandomStringUtils.randomAlphabetic(GROUPE_SIZE));
        request.setId(1);
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(REQUESTS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        Integer id = requestService.createRequestService(request);

        // then
        mockServer.verify();
        assertNotNull(id);
    }

    @Test
    public void shouldDeleteRequest() throws Exception {
        // given
        Integer id = 1;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(REQUESTS_URL + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        int result = requestService.deleteRequestService(id);
        // then
        mockServer.verify();
        assertTrue(1 == result);
    }

    private Request create(int index, int id) {
        Request request = new Request();
        request.setIdR(index);
        request.setId(id);
        return request;
    }
}
