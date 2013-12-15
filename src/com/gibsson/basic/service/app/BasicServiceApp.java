package com.gibsson.basic.service.app;

import android.app.Application;
import android.os.ServiceManager;
import android.util.Log;

import com.gibsson.basic.service.lib.IBasicService;

public class BasicServiceApp extends Application {
  private static final String TAG = "BasicServiceApp";
  private static final String REMOTE_SERVICE_NAME = IBasicService.class.getName();
  private IBasicServiceImpl serviceImpl;

  public void onCreate() {
    super.onCreate();
    this.serviceImpl = new IBasicServiceImpl(this);
    ServiceManager.addService(REMOTE_SERVICE_NAME, this.serviceImpl);
    Log.d(TAG, "Registered [" + serviceImpl.getClass().getName() + "] as [" + REMOTE_SERVICE_NAME + "]");
  }

  public void onTerminate() {
    super.onTerminate();
    Log.d(TAG, "Terminated");
  }
}
