package com.URLshortner.shawty;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Base64;

public class Hash {
    public static String getKey(String str) {
        byte[] msg={}, encodedBytes={};
        try{
            msg = str.getBytes("UTF-8");
        } 
        catch (java.io.IOException e){
            e.printStackTrace();
        } 
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Hash = md.digest(msg);
            encodedBytes = Base64.getEncoder().encode(md5Hash);
        }
        catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String temp  = new String(encodedBytes);   
        String res="";
        int i=0;
        while(i<6) {
            if (Character.isDigit(temp.charAt(i)) || Character.isLetter(temp.charAt(i))  ) {
                res += temp.charAt(i);
            }
            i++;
        }
        return res;

    }
}