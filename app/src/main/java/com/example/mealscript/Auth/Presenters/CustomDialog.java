package com.example.mealscript.Auth.Presenters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.example.mealscript.Home.Views.HomeActivity;

public class CustomDialog {
    private Context context;
    private AlertDialog.Builder builder;
    private String title , message ,positiveMessage ;
    private DialogInterface.OnClickListener listener;


    public CustomDialog(Context context) {
        this.context = context;
        this.builder = new AlertDialog.Builder(context);
        this.title="";
        this.message = "";
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setPositiveButton(String positiveMessage, DialogInterface.OnClickListener listener ){
        this.positiveMessage=positiveMessage;
        this.listener = listener;
    }

    public void setUpDialog(){
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveMessage ,listener );
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
    public void showDialog(){
        setUpDialog();
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
