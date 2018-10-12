package com.JSPath.service;

import com.JSPath.bean.JSPathBean;
import com.JSPath.repository.JSPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JSPathServiceImpl implements JSPathService {

    @Autowired
    private JSPathRepository repository;

    public JSPathBean repair(){
        return repository.repair();
    }
}
