package com.babolforfun.volley.requestvolley.controllers;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.babolforfun.volley.requestvolley.constants.Constants;

public class ApplicationController extends Application {

    /** Instance of the current application. */
    private static ApplicationController    instance;
    private static SharedPreferences        preferences;
    private static SharedPreferences.Editor editor;


    /**
     * Constructor.
     */
    public ApplicationController() {
        instance    = this;
    }

    /**
     * Gets the application context.
     *
     * @return the application context
     */
    public static Context getContext() {
        if (instance == null) {
            instance = new ApplicationController();
        }
        return instance;
    }

    /**
     * Get shared preference
     * @return shared preference
     */
    public static SharedPreferences getPreferences(){
        if (preferences == null)
            preferences = getContext().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        return preferences;
    }

    /**
     * Get editor
     * @return editor
     */
    public static SharedPreferences.Editor getEditor(){
        if (preferences == null)
            preferences = getContext().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        if (editor == null)
            editor = preferences.edit();
        return editor;
    }

    /**
     * Display toast message
     * @param data msg to show
     */
    public static void showToast(String data) {
        Toast.makeText(getContext(), data, Toast.LENGTH_LONG).show();
    }

}
