package io.github.viv2d2.findride;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class SplashActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    protected static SharedPreferences splashLogin;
    private static boolean fbLoggedin;
    private static boolean jhedLoggedin;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        splashLogin = getDefaultSharedPreferences(getApplicationContext());
        fbLoggedin = splashLogin.getBoolean("Facebook",false);
        jhedLoggedin = splashLogin.getBoolean("JHED",false);





        new Handler().postDelayed(new Runnable(){
            public void run() {

                if(fbLoggedin && jhedLoggedin) {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                }
                else {
                /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
