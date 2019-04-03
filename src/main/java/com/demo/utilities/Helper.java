package com.demo.utilities;

import java.awt.image.BufferedImage;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Helper {


    /**
     * Get OS info
     * @return string value of system OS
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

    public static String generateUniqueValue ()
    {

        final AtomicLong TS = new AtomicLong();
        long micros = System.currentTimeMillis() * 1000;

        long value = TS.get();
        if (micros <= value)
            micros = value+1;
        /*if (TS.compareAndSet(value, micros))
        	System.out.println(micros);*/

        return String.valueOf(micros);
    }

    /**
     * Compares two images pixel by pixel.
     *
     * @param imgA the first image.
     * @param imgB the second image.
     * @return whether the images are both the same or not.
     */
    public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        // The images must be the same size.
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        int width  = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }



}
