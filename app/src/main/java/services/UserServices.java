package services;

import android.content.Context;
import android.content.SharedPreferences;
import model.User;

public class UserServices {
    SharedPreferences sharedPreferences;

    public boolean saveCurrentUser(Context v,String key,User user){
        sharedPreferences=v.getSharedPreferences(key,Context.MODE_PRIVATE);
        SharedPreferences.Editor userEditor = sharedPreferences.edit();
        userEditor.putString("_id",user.getId());
        userEditor.putString("email",user.getEmail());
        userEditor.putString("first_name",user.getFirst_name());
        userEditor.putString("last_name",user.getLast_name());
        userEditor.apply();
        return true;
    }

}
