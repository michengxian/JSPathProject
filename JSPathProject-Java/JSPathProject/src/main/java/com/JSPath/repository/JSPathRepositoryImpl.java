package com.JSPath.repository;

import com.JSPath.bean.JSPathBean;
import com.JSPath.controller.JSPathController;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository
public class JSPathRepositoryImpl implements JSPathRepository {

    public JSPathBean repair() {
        JSPathBean bean = new JSPathBean();

        String classPath = JSPathController.class.getResource("/").getPath();
        File JSPathpath = new File(classPath + "JSPatch.js");
        String JSPathJS = "";


        if (JSPathpath.exists()) {
            try {
                FileReader fr = new FileReader(JSPathpath);
                InputStream is = new FileInputStream(JSPathpath);
                int size = is.available();
                char[] a = new char[size];
                fr.read(a);
                for (char c : a) {
                    JSPathJS = JSPathJS + c;
                }
                is.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        File repairPath = new File(classPath + "demo.js");
        String repairJS = "";
        if (repairPath.exists()){
            try {
                FileReader fr = new FileReader(repairPath);
                InputStream is=new FileInputStream(repairPath);
                int size=is.available();
                char[] a = new char[size];
                fr.read(a);
                for (char c : a) {
                    repairJS=repairJS+c;
                }
                is.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        bean.setJSPathJS(JSPathJS);
        bean.setRepairJS(repairJS);
        return bean;
    }
}
