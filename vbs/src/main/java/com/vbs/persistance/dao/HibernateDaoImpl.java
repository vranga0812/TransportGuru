package com.vbs.persistance.dao;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.entities.Address;
import com.vbs.persistance.entities.OTP;
import com.vbs.persistance.entities.User;
import com.vbs.persistance.entities.UserCredentials;

/**
 * @author DL
 * Jun 19 2015
 */

@Repository
@Primary
public class HibernateDaoImpl implements DatabaseDao {

	@Autowired(required=true)
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.vbs.persistance.dao.DatabaseDao#createUser(com.vbs.persistance.entities.User, com.vbs.persistance.entities.Address, com.vbs.persistance.entities.OTP)
	 */
	@Override
	public boolean createUser(UserCredentials cred, OTP otp) {
		
		System.out.println("HibernateDaoImpl.createUser()");
		boolean result = false;
		User user = cred.getUser();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Set<Address> addresses = user.getAddress();
		user.setAddress(null);
		System.out.println(session.save(user));
		session.save(cred);
		System.out.println(user);
		System.out.println("addresses:"+addresses);
		for(Address address : addresses ){
			address.setUser_id(user.getUserId());
			session.save(address);
		}
		otp.setUser_id(user.getUserId());
		session.save(otp);
		tx.commit();
		session.flush();
		session.close();
		result = true;
		return result;
	}
	
	@Override
	public void saveOrUpdate(Object obj) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(obj);
		tx.commit();
		session.close();

	}

	@Override
	public boolean isUserNameAvailable(String username) {
		UserCredentials cred = this.getCustomerProfile(username);
		if(cred != null)
			return false;
		else
			return true;
	}

	@Override
	public UserCredentials getCustomerProfile(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserCredentials as cred where cred.username= :userName");
		query.setParameter("userName", username);
		UserCredentials cred = (UserCredentials) query.uniqueResult();
		session.close();
		return cred;
	}

	public User getCustomerProfile(String email, boolean isEmail) throws VbsException{
		if(!isEmail)
			throw new VbsException("isEmail parameter cannot be false");
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User as user where user.email= :email");
		query.setParameter("email", email);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	
	@Override
	public void saveOTP(User user, String otp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOTP(String username, String otp) {
		// TODO Auto-generated method stub

	}

	

}
