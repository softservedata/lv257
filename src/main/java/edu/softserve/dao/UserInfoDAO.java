package edu.softserve.dao;

import java.util.List;

import edu.softserve.entity.User;
import edu.softserve.entity.UserRole;
import edu.softserve.model.UserInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserInfoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public UserInfoDAO() {

    }

    public UserInfo findUserInfo(String userName) {
        String sql = "Select new " + UserInfo.class.getName() + "(u.username,u.password) "//
                + " from " + User.class.getName() + " u where u.username = :username ";
        System.out.println("HALPPPPPP");
        System.out.println("HALPPPPPP");
        System.out.println("HALPPPPPP");
        System.out.println(sql);

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter("username", userName);

        return (UserInfo) query.uniqueResult();
    }

    public List<String> getUserRoles(String userName) {
        String sql = "Select r.userRole "//
                + " from " + UserRole.class.getName() + " r where r.user.username = :username ";

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter("username", userName);

        List<String> roles = query.list();

        return roles;
    }

    /*public createUser(UserInfo userInfo) {

        String sql = "insert into User (username, password) select userInfo.username userinfofrom UserInfo userInfo ";

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);

    }*/

}
