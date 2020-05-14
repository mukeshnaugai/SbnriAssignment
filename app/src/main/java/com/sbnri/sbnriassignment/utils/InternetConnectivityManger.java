package com.sbnri.sbnriassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetConnectivityManger {


        public static boolean isConnectingToInternet(Context context){

            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected() == true)
            {
                return true;
            }

            return false;


    }
}
