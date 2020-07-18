package validation;

public class Validator {

    public boolean isValidEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    public boolean isValidMinMax(String string,int min,int max){
        if (string.length()<min)return false;
        if (string.length()>max)return false;
        return true;
    }
    public boolean isValidConfirmPassword(String password,String confirmPassword){
        return password.equals(confirmPassword);
    }
}
