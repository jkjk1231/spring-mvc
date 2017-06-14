package com.zbw.springtest.log4j;

import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class SLog4jConfigListener extends Log4jConfigListener {

    public SLog4jConfigListener() {
        super();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        initLogFile();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

    private void initLogFile() {
        System.setProperty("log.dir", "../log/");

//        System.setProperty("log_path", filePath);
//        Properties props = new Properties();
//        FileInputStream istream = null;
//        try {
//            istream = new FileInputStream(filePath);
//            props.load(istream);
//            if(istream != null) {
//                istream.close();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 设置路径
//        String logFile = prefix + props.getProperty("log4j.appender.E.File");
//        props.setProperty("log4j.appender.E.File", logFile);
//        // 装入log4j配置信息
////        PropertyConfigurator.configure(props);
    }
}
