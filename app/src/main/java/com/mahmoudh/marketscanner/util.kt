package com.mahmoudh.marketscanner

import android.app.Activity
import android.app.AlertDialog

class LoadingDialog(var activity: Activity){
    var loadingDialog: AlertDialog = AlertDialog.Builder(activity)
        .setCancelable(false)
        .setView(activity.layoutInflater.inflate(R.layout.progress_dialog,null))
        .create()
    public fun show()=loadingDialog.show()
    public fun cancel()=loadingDialog.cancel()
}