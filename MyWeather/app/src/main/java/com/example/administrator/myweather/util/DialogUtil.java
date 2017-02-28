package com.example.administrator.myweather.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.administrator.myweather.R;

/**
 * Created by zhengwuy on 2017/2/28.
 * Emali: 963460692@qq.com
 * description:
 */

public class DialogUtil {

    public static Dialog creatChangeAreaDialog(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(R.string.hint).setMessage(R.string.choose_area_dialog_message)
                .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(R.string.confirm, listener)
                .create();
    }
}
