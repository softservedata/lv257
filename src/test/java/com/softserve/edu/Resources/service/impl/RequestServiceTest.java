package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.impl.ResourceRequestDAOImpl;
import com.softserve.edu.Resources.dao.impl.UserDAOImpl;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.entity.Role;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.service.impl.MailSenderService;
import com.softserve.edu.Resources.service.impl.RequestMessageHandler;
import com.softserve.edu.Resources.service.impl.RequestService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.verification.VerificationMode;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class RequestServiceTest {

    private static final String USER_EMAIL = "ResourceAdmin";
    private static final Long ID = 0L;
    private static Optional<ResourceRequest> request;

    private static List<ResourceRequest> requests;
    private static List<ResourceRequest> expected;
    private static User resourceAdmin;
    private static User register;
    private static Date date;


    @InjectMocks
    private RequestService requestService;

    @Mock
    private ResourceRequestDAOImpl resourceRequestDAO;


    @Mock
    private UserDAO userDAO;

    @Mock
    Message message;

    @Mock
    RequestMessageHandler messageHandler;

    @Mock
    MailSenderService mailSender;


//    @BeforeClass
//    public static void SetUp() {
//        requests = new ArrayList<>();
//        requests.add(new ResourceRequest().setStatus(ResourceRequest.Status.NEW));
//        requests.add(new ResourceRequest().setStatus(ResourceRequest.Status.ACCEPTED));
//        requests.add(new ResourceRequest().setStatus(ResourceRequest.Status.DECLINED));
//        requests.add(new ResourceRequest().setStatus(ResourceRequest.Status.TO_REFINEMENT));
//        expected = new ArrayList<>();
//        resourceAdmin = new User();
//        register = new User();
//        register.setUsername("Register");
//        resourceAdmin.setUsername(USER_EMAIL);
//        date = new Date();
//        request = Optional.ofNullable(new ResourceRequest().setResourceType("TestType").setCode("1234567890")
//                .setStatus(ResourceRequest.Status.NEW).setRegister(register).setResourcesAdmin(null)
//                .setDescription("description").setId(ID).setUpdate(new Date()));
//
//
//    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getByIdNullTest() {
        Optional<ResourceRequest> requestEmpty = Optional.ofNullable(new ResourceRequest());
        Mockito.when(resourceRequestDAO.findById(ID)).thenReturn(requestEmpty);
        assertEquals(requestEmpty.get(), requestService.getRequestById(ID));
    }


//    @Test
//    public void getByIdTest() {
//        Mockito.when(resourceRequestDAO.findById(ID)).thenReturn(request);
//        assertEquals(request.get(), requestService.getRequestById(ID));
//    }


//    @Test
//    public void getNewRequestsTest() {
//        expected.clear();
//        expected.add(requests.get(0));
//        Mockito.when(resourceRequestDAO.findAll()).thenReturn(requests);
//        assertEquals(expected, requestService.getNewResourcesRequest());
//    }


//    @Test
//    public void getHistoryRequestsTest() {
//        expected.clear();
//        expected.add(requests.get(1));
//        expected.add(requests.get(2));
//        Mockito.when(resourceRequestDAO.findAll()).thenReturn(requests);
//        assertEquals(expected, requestService.getHistoryResourcesRequest());
//    }


//    @Test
//    public void assignResourceAdminTest() throws Exception {
//        Mockito.when(userDAO.findByEmail(USER_EMAIL)).thenReturn(resourceAdmin);
//        Mockito.when(resourceRequestDAO.findById(ID)).thenReturn(request);
//
//        whenNew(Date.class).withAnyArguments().thenReturn(date);
//        requestService.assignResourceAdmin(ID, USER_EMAIL);
//        verify(resourceRequestDAO, times(1))
//                .makePersistent(request.get().setResourcesAdmin(resourceAdmin).setUpdate(date));
//
//
//    }

//    @Test
//    public void responceTest() throws Exception {
//        Mockito.when(message.getId_request()).thenReturn(ID);
//        Mockito.when(resourceRequestDAO.findById(ID))
//                .thenReturn(Optional.of(request.get().setResourcesAdmin(resourceAdmin)));
//        Mockito.when(message.getRequestStatus()).thenReturn(ResourceRequest.Status.ACCEPTED);
//
//        whenNew(Date.class).withAnyArguments().thenReturn(date);
//        requestService.response(message);
//        verify(resourceRequestDAO, times(1))
//                .makePersistent(request.get()
//                        .setResourcesAdmin(resourceAdmin).setUpdate(date).setStatus(ResourceRequest.Status.ACCEPTED));
//        verify(messageHandler, times(1)).setMessage(message);
//        verify(mailSender, times(1)).sendMessage(messageHandler);
//    }
}
