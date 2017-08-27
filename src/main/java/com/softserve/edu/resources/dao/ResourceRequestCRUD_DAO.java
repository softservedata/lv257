package com.softserve.edu.resources.dao;

import com.softserve.edu.resources.entity.ResourceRequest;


public interface ResourceRequestCRUD_DAO {

     void persistRequest(ResourceRequest request);

     void updateRequest(ResourceRequest request);
}
