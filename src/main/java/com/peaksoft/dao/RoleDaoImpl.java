package com.peaksoft.dao;

import com.peaksoft.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    public final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles(){
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Role").list();
    }

    @Override
    @Transactional
    public List<String> getRoleNamesToList() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("select  role from Role ").getResultList();
    }

    @Override
    public Role getRoleByName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select  role from Role role where role.role=:role");
        query.setParameter("role", username);
        return (Role) query.getSingleResult();
    }
}