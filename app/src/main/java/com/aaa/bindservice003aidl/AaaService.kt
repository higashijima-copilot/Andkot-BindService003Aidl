package com.aaa.bindservice003aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.util.Log

class AaaService : Service() {
  private var _iAaaServiceCallback: IAaaServiceCallback? = null

  override fun onBind(intent: Intent): IBinder {
    return object: IAaaService.Stub() {
                    override fun getPid(): Int {
                      Log.d("aaaaa", "aaaaa getPid() ret=111")
                      return Process.myPid()
                    }

                    override fun basicTypes(
                      anInt: Int, aLong: Long, aBoolean: Boolean,
                      aFloat: Float, aDouble: Double, aString: String?) {
                      /* ひとまずログ出力だけ */
                      Log.d("aaaaa", "aaaaa AaaService::basicTypes() anInt=$anInt aLong=$aLong aBoolean=$aBoolean aFloat=$aFloat aDouble=$aDouble aString=$aString")
                      _iAaaServiceCallback?.xxxValueChanged(666)
                    }

                    override fun setAaaServiceCallback(callback: IAaaServiceCallback) {
                      _iAaaServiceCallback = callback
                    }
                  }
  }


}