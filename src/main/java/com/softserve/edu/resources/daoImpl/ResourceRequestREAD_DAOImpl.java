package com.softserve.edu.resources.daoImpl;

import com.softserve.edu.resources.dao.ResourceRequestREAD_DAO;
import com.softserve.edu.resources.entity.ResourceRequest;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ResourceRequestREAD_DAOImpl implements ResourceRequestREAD_DAO {

    private static SessionFactory factory;

    //search on client
    public List<ResourceRequest> getAllRequests() {

        return factory.getCurrentSession().createCriteria(ResourceRequest.class).list();
    }


    //search in db
    public List<ResourceRequest> getNewResourcesRequest() {
        List<ResourceRequest> requests = new ArrayList<>();
        try {
            Field entityStatusField = ResourceRequest.class.getDeclaredField("status");
            Column columnStatus = entityStatusField.getDeclaredAnnotation(Column.class);
            String columnStatusName = columnStatus.name();

            Field entityNotifyField = ResourceRequest.class.getDeclaredField("notifyExecutor");
            Column columnNotifying = entityNotifyField.getDeclaredAnnotation(Column.class);
            String columnNotifyingName = columnNotifying.name();

            Criteria cr = factory.getCurrentSession().createCriteria(ResourceRequest.class);
            Criterion isNew = Restrictions.eq(columnStatusName, "NEW");
            Criterion isAccessibleToAdmin = Restrictions.gt(columnNotifyingName, true);

            LogicalExpression andExp = Restrictions.and(isAccessibleToAdmin, isNew);
            cr.add(andExp);

            requests = criteriaSearch(cr);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public List<ResourceRequest> getProcessedRequest() {
        List<ResourceRequest> requests = new ArrayList<>();
        try {
            Field entityStatusField = ResourceRequest.class.getDeclaredField("status");
            Column column = entityStatusField.getDeclaredAnnotation(Column.class);
            String columnName = column.name();
            Criteria cr = factory.getCurrentSession().createCriteria(ResourceRequest.class).add(Restrictions.disjunction());

            Criterion isDeclined = Restrictions.eq(columnName, "DECLINED");
            Criterion isAccepted= Restrictions.eq(columnName,"ACCEPTED");

            LogicalExpression orExp = Restrictions.or(isDeclined, isAccepted);
            cr.add(orExp);

            requests = criteriaSearch(cr);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return requests;
    }

    private List<ResourceRequest> criteriaSearch(Criteria criteria) {
        List<ResourceRequest> requests = criteria.list();
        return requests;
    }

}
