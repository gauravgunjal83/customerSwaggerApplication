package com.csi.dao;

import com.csi.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDaoImpl implements  CustomerDao {

    private static SessionFactory factory=new AnnotationConfiguration().configure().buildSessionFactory();
    @Override
    public void saveData(Customer customer) {
        Session session=factory.openSession();
        Transaction transaction= session.beginTransaction();

        session.save(customer);
        transaction.commit();
    }

    @Override
    public Customer getDataById(int custId) {
        Session session= factory.openSession();
        return  (Customer) session.get(Customer.class,custId);
    }

    @Override
    public List<Customer> getAllData() {
        Session session=factory.openSession();
        return session.createQuery("from Customer").list();
    }



    @Override
    public void updateData(int custId, Customer customer) {

        Session session = factory.openSession();
        Transaction transaction= session.beginTransaction();

        Customer customer1=getDataById(custId);

        customer1.setCustName(customer.getCustName());
        customer1.setCustAccBal(customer.getCustAccBal());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustDob(customer.getCustDob());

        session.update(customer1);
        transaction.commit();
    }

    @Override
    public void deleteAllData() {

        Session session= factory.openSession();

        for (Customer customer: getAllData()) {
            Transaction transaction= session.beginTransaction();
            session.delete(customer);
            transaction.commit();
        }
    }
}
