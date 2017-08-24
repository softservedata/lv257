package edu.softserve;

import edu.softserve.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        try {
            load(sessionFactory);
        } finally {
            sessionFactory.close();
        }

    }


    private static void load(SessionFactory sessionFactory) {
        System.out.println("-- loading persons --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<User> persons = session.createQuery("FROM User").list();
        for (User x: persons) {
            System.out.printf("- %s%n", x);
            System.out.println(x.getUsername() +" " + x.getPassword());
            System.out.println();
        }
        session.close();
    }
}
