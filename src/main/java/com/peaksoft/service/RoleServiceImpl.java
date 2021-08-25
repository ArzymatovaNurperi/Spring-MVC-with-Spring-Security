package com.peaksoft.service;

import com.peaksoft.dao.RoleDao;
import com.peaksoft.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    public final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public List<String> getRoleNamesToList() {
        return roleDao.getRoleNamesToList();
    }

    @Override
    @Transactional
    public Role getRoleByName(String username) {
        return roleDao.getRoleByName(username);
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

}
