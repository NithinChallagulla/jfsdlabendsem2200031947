package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setAge(30);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setAge(25);
        customer2.setLocation("Los Angeles");

        session.save(customer1);
        session.save(customer2);

        tx.commit();

        Criteria criteria = session.createCriteria(Customer.class);

        List<Customer> customers;

        criteria.add(Restrictions.eq("name", "Alice"));
        customers = criteria.list();
        System.out.println("Customers with name Alice: " + customers);

        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.ne("location", "New York"));
        customers = criteria.list();
        System.out.println("Customers not in New York: " + customers);

        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 28));
        customers = criteria.list();
        System.out.println("Customers older than 28: " + customers);

        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 20, 30));
        customers = criteria.list();
        System.out.println("Customers aged between 20 and 30: " + customers);

        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("email", "%example.com"));
        customers = criteria.list();
        System.out.println("Customers with emails ending in example.com: " + customers);

        session.close();
        factory.close();
    }
}
