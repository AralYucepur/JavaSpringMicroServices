package com.aral.utility;

import java.util.UUID;

public class ActivationCode {

    public static String activationCodeGenerator(){
        String randomCode = UUID.randomUUID().toString();
        String[] noHyphen = randomCode.split("-");
        StringBuilder firstLetters = new StringBuilder();
        for (String part : noHyphen) {
            if (!part.isEmpty()) {
                firstLetters.append(part.charAt(0));
            }
        }
        return firstLetters.toString();
    }
}
