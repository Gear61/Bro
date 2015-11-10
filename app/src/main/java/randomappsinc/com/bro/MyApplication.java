package randomappsinc.com.bro;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Alex Chiou on 9/29/15.
 */
public class MyApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
        Iconify.with(new FontAwesomeModule());
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
