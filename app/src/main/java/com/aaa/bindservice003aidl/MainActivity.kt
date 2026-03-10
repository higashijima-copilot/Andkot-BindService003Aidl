package com.aaa.bindservice003aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  /********* 関数定義 *********/
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    /* Service接続/切断 */
    findViewById<Button>(R.id.btn_connect2Server).setOnClickListener {
      if(findViewById<Button>(R.id.btn_connect2Server).text == resources.getString(R.string.connecting)) {
        findViewById<Button>(R.id.btn_connect2Server).text = resources.getString(R.string.disconnecting)
        Log.d("aaaaa", "aaaaa unbindService() called")
        unbindService(_con)
      }
      else {
        Log.d("aaaaa", "aaaaa bindService() called", )
        findViewById<Button>(R.id.btn_connect2Server).text = resources.getString(R.string.connecting)
        bindService(Intent(this, AaaService::class.java), _con, Context.BIND_AUTO_CREATE)
      }
    }

    /* Serviceへ送信 */
    findViewById<Button>(R.id.btn_send2Server).setOnClickListener{
      val ret = iAaaService?.pid
      Log.d("aaaaa", "aaaaa Service送信 $ret")
      iAaaService?.basicTypes(1,2,true,3f,4.0,"aaaa")
    }
  }

  /********* メンバ変数定義 *********/
  var iAaaService: IAaaService? = null
  private val _con = object: ServiceConnection {
                      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                        Log.d("aaaaa", "aaaaa ServiceConnection::onServiceConnected()", )
                        iAaaService = IAaaService.Stub.asInterface(service)
                        iAaaService?.setAaaServiceCallback(_IAaaServiceCallback)
                      }

                      override fun onServiceDisconnected(name: ComponentName?) {
                        Log.d("aaaaa", "aaaaa ServiceConnection::onServiceDisconnected()", )
                        iAaaService = null
                      }
                    }

  private val _IAaaServiceCallback = object: IAaaServiceCallback.Stub() {
                                          override fun xxxValueChanged(aval: Int) {
                                            Log.d("aaaaa", "aaaaa MainActivity::xxxValueChanged($aval)")
                                          }
                                        }
}