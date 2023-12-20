package br.edu.ifpb.pweb2.sisyphus.util;

import org.mindrot.jbcrypt.BCrypt;

public abstract class PasswordUtil {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        if(BCrypt.checkpw(password, hashedPassword)){
            return true;
        }else{
            return false;
        }
    }
}
