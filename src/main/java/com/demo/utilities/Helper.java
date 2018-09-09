package com.demo.utilities;

import com.demo.testreport.TestCaseId;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

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

    public static String getStringValue(Object input){

        if(input != null){

            if(input instanceof Integer){
                return Integer.toString((int)input);
            }else if(input instanceof String){
                return (String)input;

            }else if(input instanceof Long){
                return Long.toString(((long)input));

            }else if(input instanceof Float){
                return Float.toString((float)input);


            }else if(input instanceof Double){
                return Double.toString((Double)input);

            }else if(input instanceof Boolean){
                return Boolean.toString((boolean)input);

            }
            else{
                return null;
            }
        }

        return null;

    }

    public static Object[][] populateTestCaseId(Object[][] inputArray){
        int rowCount = inputArray.length;
        int columnCount = inputArray[0].length;

        Object[][] outputArray = new Object[rowCount][columnCount];

        for (int row = 0; row < inputArray.length; row++) {
            for (int col = 0; col < inputArray[row].length; col++) {
                if(col == 0){
                    String testCaseName = (String) inputArray[row][col];
                    TestCaseId testCaseId = new TestCaseId();
                    testCaseId.setTestName(testCaseName);
                    outputArray[row][col] = testCaseId;
                }else{
                    outputArray[row][col] = inputArray[row][col];
                }
            }
        }
        return outputArray;
    }
}
