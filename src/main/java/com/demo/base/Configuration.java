package com.demo.base;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    protected Properties configVO = null;

    /* Default Constructor
     * */
    public Configuration(){

    }


    /* Constructor to Load Configuration/Property file
     * */
    public Configuration(String configPath) {
        configVO = new Properties();

        configVO = loadConfiguration(configPath);

    }


    /**
     * Load the *.properties files
     * @param resourcePath configuration file patth
     * @return Properties Object
     */
    public Properties loadConfiguration(String resourcePath)  {
        Properties propVO = new Properties();
        InputStream inputFile = null;

        try {

            if(StringUtils.isNotEmpty(resourcePath)) {
                inputFile = new FileInputStream(resourcePath);
                propVO.load(inputFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }catch (IOException ioe) {
            ioe.printStackTrace();

        }

        return propVO;

    }

    /**
     * Get the data from Configuration Object
     * @param key key value
     * @return configuration data
     */
    public String getConfiguration(String key){


        if(StringUtils.isNotEmpty(key)
                && !(configVO.isEmpty())
                && configVO.containsKey(key)){

            return  configVO.getProperty(key);

        }else{
            return null;
        }

    }


    /**
     * Get the Configuration Object
     * @return Properties
     */
    public Properties getConfiguration(){

        return configVO;

    }






}
