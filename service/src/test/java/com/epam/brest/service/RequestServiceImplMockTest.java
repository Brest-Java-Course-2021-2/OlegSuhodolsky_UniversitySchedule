package com.epam.brest.service;

import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RequestServiceImplMockTest {

    @Mock
    private RequestService requestService;

    @Mock
    private DaoUserAPI daoRequest;

    @AfterEach
    public void check() {
        Mockito.verifyNoMoreInteractions(daoRequest);
    }

    @Test
    public void findAll() {
        Request request = new Request();
        int id = 0;
        List<Request> list = Collections.singletonList(request);
        Mockito.when(requestService.getAllRequestsByIdService(id)).thenReturn(list);
        List<Request> result = requestService.getAllRequestsByIdService(id);
        Mockito.verify(requestService).getAllRequestsByIdService(id);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(result.get(0), request);
    }

    @Test
    public void findRequestByID() {
        Request request = new Request();
        int idR = 1;
        Mockito.when(requestService.getRequestByIdService(idR)).thenReturn(request);
        Request result = requestService.getRequestByIdService(idR);
        Mockito.verify(requestService).getRequestByIdService(idR);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, request);
    }

    @Test
    public void createRequest() {
        Request request = new Request();
        Integer id = 1;
        Mockito.when(requestService.createRequestService(request)).thenReturn(id);
        Integer result = requestService.createRequestService(request);
        Mockito.verify(requestService).createRequestService(request);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, id);
    }

    @Test
    public void updateRequest() {
        Request request = new Request();
        Integer idR = 1;
        Mockito.when(requestService.updateRequestService(request)).thenReturn(idR);
        Integer result = requestService.updateRequestService(request);
        Mockito.verify(requestService).updateRequestService(request);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, idR);
    }

    @Test
    public void deleteUser() {
        Integer idR = 1;
        Mockito.when(requestService.deleteRequestService(idR)).thenReturn(1);
        Integer result = requestService.deleteRequestService(idR);
        Mockito.verify(requestService).deleteRequestService(idR);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, 1);
    }

}
