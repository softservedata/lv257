package  com.softserve.edu.resources.serviceImpl;


import com.softserve.edu.resources.daoImpl.ResourceRequestCRUD_DAOimpl;
import com.softserve.edu.resources.daoImpl.ResourceRequestREAD_DAOimpl;
import com.softserve.edu.resources.dto.MessageDTO;
import com.softserve.edu.resources.dto.ResourceRequestDTO;
import com.softserve.edu.resources.dto.Status;
import com.softserve.edu.resources.entity.ResourceRequest;
import com.softserve.edu.resources.entity.User;
import com.softserve.edu.resources.service.ResourceRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 25.08.2017.
 */
public class ResourceRequestServiceImpl implements ResourceRequestService {


   @Autowired
    private ResourceRequestCRUD_DAOimpl requestCRUD_DAO;

    @Autowired
    private ResourceRequestREAD_DAOimpl requestREAD_DAO;

    @Override
    public void assignResourceAdmin(ResourceRequestDTO request, User resourceAdmin) {
        request.setResourcesAdmin(resourceAdmin);
        requestCRUD_DAO.update(new ResourceRequest(request));
    }

    @Override
    public void toRefinement(ResourceRequestDTO request, MessageDTO message){
        request.setUpdate( new Date(new java.util.Date().getTime()));
        request.setNotifyExecutor(false);
        sendMessage(message);
        requestCRUD_DAO.update(new ResourceRequest(request));
    };

    @Override
    public void declined(ResourceRequestDTO request, MessageDTO message){
        request.setStatus(Status.DECLINED);
        request.setUpdate( new Date(new java.util.Date().getTime()));
        sendMessage(message);
        requestCRUD_DAO.update(new ResourceRequest(request));
    }

    @Override
    public void accepted(ResourceRequestDTO request, MessageDTO message){
        request.setStatus(Status.ACCEPTED);
        request.setUpdate( new Date(new java.util.Date().getTime()));
        sendMessage(message);
        requestCRUD_DAO.update(new ResourceRequest(request));
    }


    private void sendMessage(MessageDTO messageDTO){

    }



    @Override
    public List<ResourceRequestDTO> getResourcesRequest() {
        List<ResourceRequestDTO> requestDTOS = new ArrayList<>();
        for(ResourceRequest request:requestREAD_DAO.getAllRequests()){
            requestDTOS.add(new ResourceRequestDTO(request));
        }
        return requestDTOS;
    }


    //in db
    @Override
    public List<ResourceRequestDTO> getNewResourcesRequest() {
        List<ResourceRequestDTO> requestDTOS = new ArrayList<>();
        for(ResourceRequest request:requestREAD_DAO.getNewResourcesRequest()){
            requestDTOS.add(new ResourceRequestDTO(request));
        }
        return requestDTOS;
    }

    @Override
    public List<ResourceRequestDTO> getProcessedResourcesRequest() {
        List<ResourceRequestDTO> requestDTOS = new ArrayList<>();
        for(ResourceRequest request:requestREAD_DAO.getProcessedRequest()){
            requestDTOS.add(new ResourceRequestDTO(request));
        }
        return requestDTOS;
    }

    //on client
    public List<ResourceRequestDTO> getNewResourcesRequest1() {
        List<ResourceRequestDTO> requestDTOS =getResourcesRequest();
        requestDTOS.stream().filter(request->request.getStatus().equals(Status.NEW)&request.isNotifyExecutorTrue())
                .collect(Collectors.toList());
        return requestDTOS;
    }

    public List<ResourceRequestDTO> getHistoryResourcesRequest1() {
        List<ResourceRequestDTO> requestDTOS =getResourcesRequest();
        requestDTOS.stream().filter(request->request.getStatus().equals(Status.ACCEPTED)
                |request.getStatus().equals(Status.DECLINED)).collect(Collectors.toList());
        return requestDTOS;
    }


}
