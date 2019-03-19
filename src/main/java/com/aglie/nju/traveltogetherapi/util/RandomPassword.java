package com.aglie.nju.traveltogetherapi.util;

import java.util.Random;

public class RandomPassword {
    public static String createRandomPassword(int passwordLength){
        String newPassword = "";
        Random ran = new Random();
        for(int i = 0;i < passwordLength;i++){
            int code = ran.nextInt(9);
            newPassword += code;
        }
        return newPassword;
    }
}
