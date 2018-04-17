package com.springapp.mvc.datasource;

import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.Roles;
import com.springapp.mvc.model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Optional<User> getUserByName(String username) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select u from User u join fetch u.roles r where u.username=:name");
        query.setParameter("name", username);

        return query.getResultList().stream().findFirst();
    }

    public Roles getRole(Role role) {
        TypedQuery<Roles> query = sessionFactory.getCurrentSession()
                .createQuery("select u from Roles u where u.role=:name");
        query.setParameter("name", role);

        return query.getResultList().stream().findFirst().get();
    }

    public Optional<User> getUserById(int id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select u from User u where id=:id",
                        User.class);
        query.setParameter("id", id);

        return query.getResultList().stream().findFirst();
    }

    public List<User> getListOfUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .list();
    }

    public List<User> getAllByGender(Gender gender) {
        sessionFactory.getCurrentSession();
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User where gender=:gender");
        query.setParameter("gender", gender);

        return query.getResultList();
    }

    public void saveUser(User user){
        user.setRoles(new HashSet<>(Arrays.asList(getRole(Role.ROLE_USER))));
        sessionFactory.getCurrentSession().save(user);
    }

    public void editUser(User user){
        Query query = sessionFactory.getCurrentSession().createQuery("update User set first_name=:fname," +
                " last_name=:lname, password=:pass, email=:email, gender=:gender where id=:id");
        query.setParameter("fname", user.getFirstName());
        query.setParameter("lname", user.getLastName());
        query.setParameter("pass", user.getPassword());
        query.setParameter("email", user.getEmail());
        query.setParameter("gender", user.getGender());
        query.setParameter("id", getUserByName(user.getUsername()).get().getId());
        query.executeUpdate();
    }

    public void deleteUser(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
