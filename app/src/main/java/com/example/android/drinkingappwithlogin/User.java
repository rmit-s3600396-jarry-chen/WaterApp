package com.example.android.drinkingappwithlogin;

import static android.R.attr.name;

/**
 * Created by jpower707 on 28/02/2017.
 */

public class User {
    private long goal,currentIntake;
    private int favourateBottle;
    private static String name;
    private String email;

    public User( String email,String name){
        this.name = name;
        this.email = email;

    }
    public static String getName()
    {
        return name;
    }

    public long getGoal(){
        return goal;
    }

    public void setGoal(long goal)
    {
        this.goal = goal;
    }

    public long getCurrentIntake(){
        return currentIntake;
    }

    public void setCurrentIntake(long currentIntake)
    {
        this.currentIntake = currentIntake;
    }

    public int getFavourateBottle()
    {
        return favourateBottle;
    }

    public void setFavourateBottle(int favourateBottle)
    {
        this.favourateBottle = favourateBottle;
    }

}
