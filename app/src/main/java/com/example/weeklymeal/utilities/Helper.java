package com.example.weeklymeal.utilities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.weeklymeal.R;

import java.util.Objects;

public class Helper {

    public static int isDelete = 0;

//    public static void ShowLoading(Context context) {
//        dialog = new Dialog(context);
//        dialog.setContentView(R.layout.custom_dialog_loader);
//        dialog.setCancelable(false);
//        dialog.show();
//    }
//
//    public static void HideLoading() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//            dialog = null;
//        }
//    }

    public static void ShowAlert(String msg, Context context) {
        new AlertDialog.Builder(context)
//                .setTitle("Alert")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

//    @SuppressLint("SetTextI18n")
//    public static int showDeleteAlert(Context context) {
//        Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.dialog_delete_alert);
//        TextView title, cancel, delete;
//
//        title = dialog.findViewById(R.id.tv_dialog_title);
//        title.setText("Are you sure want to delete this item");
//        cancel = dialog.findViewById(R.id.tv_cancel);
//        delete = dialog.findViewById(R.id.tv_delete);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                isDelete = 0;
//            }
//        });
//        delete.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                isDelete = 1;
//            }
//        });
//
//        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//
//        return isDelete;
//    }

    public static String retrofitErrorHandler(Integer integer, Context context) {
//        HideLoading();
        Log.d("Response Error", String.valueOf(integer));
        String message = null;
        if (String.valueOf(integer).equals("400")) {
            message = "Bad Request... Please check your Internet connection!!!!";
        } else if (String.valueOf(integer).equals("401")) {
            message = "Unauthorized...Please Login again!!";
        } else if (String.valueOf(integer).equals("403")) {
            message = "Forbidden Request... The client does not have access rights to the content!!";
        } else if (String.valueOf(integer).equals("404")) {
            message = "Not Found... The server can not find the requested resource!!";
        } else if (String.valueOf(integer).equals("405")) {
            message = "Method not Allowed...The request HTTP method is known by the server but has been disabled and cannot be used for that resource.";
        } else if (String.valueOf(integer).equals("408")) {
            message = "Request Time Out... Please check your Internet connection!!";
        } else if (String.valueOf(integer).equals("500")) {
            message = "Internal server error... Please try again after some time!!";
        } else if (String.valueOf(integer).equals("502")) {
            message = "Bad Gateway... Please try again after some time!!";
        }
        ShowAlert(message, context);
        return message;
    }
}
