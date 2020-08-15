package com.cy.permissionniubility;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/14 22:43
 * @UpdateUser:
 * @UpdateDate: 2020/8/14 22:43
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class OnPermissionCallback {
    private Activity activity;

    public OnPermissionCallback(Activity activity) {
        this.activity = activity;
    }

    public abstract void onPermissionHave();

    public void onPermissionRefuse() {
    }

    public void onPermissionRefuseNoAsk() {
        authorize();
    }
    public String getAuthorizeDialogButtonPositive(){
        return "去授权";
    }
    public String getAuthorizeDialogMessage(){
        return "禁用这些权限，您可能无法使用某些功能";
    }
    public String getAuthorizeDialogButtonNegative(){
        return "取消";
    }
    public void authorize() {
        //解释原因，并且引导用户至设置页手动授权
        new AlertDialog.Builder(activity)
                .setMessage(getAuthorizeDialogMessage())
                .setPositiveButton(getAuthorizeDialogButtonPositive(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户至设置页手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",activity.getPackageName(), null);
                        intent.setData(uri);
                        activity.startActivity(intent);
                    }
                })
                .setNegativeButton(getAuthorizeDialogButtonNegative(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户手动授权，权限请求失败
                    }
                }).show();
    }
}
