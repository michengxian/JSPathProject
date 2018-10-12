package com.mobile.JSPath.service;

import com.mobile.JSPath.bean.JSPathBean;
import com.mobile.JSPath.repository.JSPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JSPathServiceImpl implements JSPathService {

    @Autowired
    private JSPathRepository repository;


    @Override
    public JSPathBean repair() {
        return repository.repair();
    }
}
