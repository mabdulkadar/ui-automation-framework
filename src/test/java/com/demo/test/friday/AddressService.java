package com.demo.test.friday;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressService {

    public static void main(String[] args) {
       // String data = "Name der Strase 25a 88489 Teststadt";
        //String data = "Winterallee 3";
        //String data = "4, rue de la revolution";
        //String data = "Calle 39 No 1540";



        //String data = "Auf der Vogelwiese 23 b";



        //String regexp = "([ a-zA-z]+) ([\\w]+) (\\d+) ([a-zA-Z]+)"; //String data = "Auf der Vogelwiese 23 b";
        //String regexp = "([a-zäöüßA-Z]*) (\\d+)"; //String data = "Am Bächle 23";



        String data = "Calle 39 No 1540";

        String regexp = "([\\d]*) ([a-zäöüßA-Z\\d]*$)"; //String data = "Auf der Vogelwiese 23 b";

        //String streetNo = "([\\d]*) ([a-zäöüßA-Z\\d]*$)"; String data = "Auf der Vogelwiese 23 b";
        //String streetNo = "^\d+(\.\d+)?"; String data = "200 Broadway Av";


        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(data);
        boolean matchFound = matcher.find();

        String groupStr="";
        if (matchFound) {

            // Get all groups for this match
            for (int i=0; i<=matcher.groupCount(); i++) {
                 groupStr = matcher.group(i);

            }


            Pattern pattern2 = Pattern.compile("([a-zäöüßA-Z]*) ([a-zäöüßA-Z\\d]*$)");
            Matcher matcher2 = pattern2.matcher(data);
            boolean matchFound2 = matcher2.find();

            if(matchFound2){
                if(StringUtils.contains(matcher2.group(0),"No")){
                    System.out.println(matcher2.group(0));
                }
            }





        }else{System.out.println("nothing found");}

        System.out.println(matcher.group(0));

        if(matchFound){

            String[] data2 = data.split("\\ ");

            //System.out.println("Street No : "+data2[(data2.length - 1)]);

        }
    }
}
