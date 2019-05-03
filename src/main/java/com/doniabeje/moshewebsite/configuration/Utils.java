package com.doniabeje.moshewebsite.configuration;

public class Utils {

   public  String name = "theasdfadsf";

    public static String minify(String content, int length){
        if (content.length() <= length){
            return content + "...";
        }

        return content.substring(0, length - 1) + "...";
    }

    public static String m(){
        return "working";
    }
}
