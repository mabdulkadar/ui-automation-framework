package com.demo.utilities;

import java.util.UUID;

public class Helper {


    /**
     * Get OS info
     * @return
     */
    public static String getOS(){

        String OS = System.getProperty("os.name").toLowerCase();


        if (OS.indexOf("win") >= 0) {
            return "win";
        } else if (OS.indexOf("mac") >= 0) {
            return "mac";
        } else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ) {
            return "unix";
        } else if (OS.indexOf("sunos") >= 0) {
            return "unix";
        } else {
            return "Your OS is not support!!";
        }
    }

    public static String generateRandomStringUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
