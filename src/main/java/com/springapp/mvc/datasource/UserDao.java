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
                .createQuery("select u from User u join fetch u.roles r where u.id=:id",
                        User.class);
        query.setParameter("id", id);

        return query.getResultList().stream().findFirst();
    }

    public List<User> getListOfUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u join fetch u.roles r")
                .list();
    }

    public List<User> getListOfUsersByAgeOver(double age) {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u join fetch u.roles r where date_part('year', current_date) - date_part('year', u.dateOfBirth)>:age")
                .setParameter("age", age)
                .list();
    }

    public List<User> getListOfUsersByAgeUnder(double age) {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u join fetch u.roles r where date_part('year', current_date) - date_part('year', u.dateOfBirth)<:age")
                .setParameter("age", age)
                .list();
    }

    public List<User> getAllByGender(Gender gender) {
        sessionFactory.getCurrentSession();
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User u join fetch u.roles r where u.gender=:gender");
        query.setParameter("gender", gender);

        return query.getResultList();
    }

    public void saveUser(User user){
        user.setRoles(new HashSet<>(Arrays.asList(getRole(Role.ROLE_USER))));
        sessionFactory.getCurrentSession().save(user);
    }

    public void editUser(User user){
        Query query = sessionFactory.getCurrentSession().createQuery("update User set first_name=:fname," +
                " last_name=:lname, password=:pass, email=:email, dateOfBirth=:date, gender=:gender where id=:id");
        query.setParameter("fname", user.getFirstName());
        query.setParameter("lname", user.getLastName());
        query.setParameter("pass", user.getPassword());
        query.setParameter("email", user.getEmail());
        query.setParameter("gender", user.getGender());
        query.setParameter("date", user.getDateOfBirth());
        query.setParameter("id", getUserByName(user.getUsername()).get().getId());
        query.executeUpdate();
    }

    public void deleteUser(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public List<User> getListOfUsersByRole(Role role) {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u join fetch u.roles r where r.role=:role")
                .setParameter("role", role)
                .list();
    }

    public void saveRole(Roles newRole) {
        sessionFactory.getCurrentSession().save(newRole);
    }

    public void editRole(Roles newRole) {
        Query query = sessionFactory.getCurrentSession().createQuery("update Roles set role=:role where id=:id");
        query.setParameter("role", newRole.getRole());
        query.setParameter("id", newRole.getId());
        query.executeUpdate();
    }

    public void deleteRole(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Roles where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
