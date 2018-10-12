package com.mobile.JSPath.repository;

import com.mobile.JSPath.bean.JSPathBean;
import org.springframework.stereotype.Repository;

import java.io.*;


@Repository
public class JSPathRepositoryImpl implements JSPathRepository {


    @Override
    public JSPathBean repair() {

        JSPathBean bean = new JSPathBean();

        String path = JSPathRepositoryImpl.class.getResource("/").getPath();
        File basePath = new File(path + "JSPatch.js");
        String base = "";

        if (basePath.exists()){
            try {
                FileReader fr = new FileReader(basePath);
                InputStream is=new FileInputStream(basePath);
                int size=is.available();
                char[] a = new char[size];
                fr.read(a);
                for (char c : a) {
                    base=base+c;
                }
                is.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        File repairPath = new File(path + "demo.js");
        String repair = "";

        if (repairPath.exists()){
            try {
                FileReader fr = new FileReader(repairPath);
                InputStream is=new FileInputStream(repairPath);
                int size=is.available();
                char[] a = new char[size];
                fr.read(a);
                for (char c : a) {
                    repair=repair+c;
                }
                is.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        bean.setBase(base);
        bean.setRepair(repair);


        return bean;
    }
}
