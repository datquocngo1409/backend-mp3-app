package com.example.backendmp3app.service.impl;

import com.example.backendmp3app.model.Admin;
import com.example.backendmp3app.repository.AdminRepository;
import com.example.backendmp3app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//Đọc comment ở File Service.
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> findAll() {
        return (List<Admin>) adminRepository.findAll();
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void remove(Long id) {
        adminRepository.deleteById(id);
    }   
}
