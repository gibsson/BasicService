package com.gibsson.basic.service.lib;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

public class BasicManager {
  private static final String TAG = "BasicManager";
  private static final String REMOTE_SERVICE_NAME = IBasicService.class.getName();
  private static final boolean DEBUG = true;
  private final IBasicService service;

  public static BasicManager getInstance() {
    return new BasicManager();
  }

  private BasicManager() {
    Log.d(TAG, "Connecting to IBasicService by name [" + REMOTE_SERVICE_NAME + "]");
    this.service = IBasicService.Stub.asInterface(
      ServiceManager.getService(REMOTE_SERVICE_NAME));
    if (this.service == null) {
      throw new IllegalStateException("Failed to find IBasicService by name [" + REMOTE_SERVICE_NAME + "]");
    }
  }

  public int getValue() {
    try {
      if (DEBUG) Log.d(TAG, "Getting the value");
      return this.service.getValue();
    } catch (RemoteException e) {
      throw new RuntimeException("Failed to get the value", e);
    }
  }

  public int setValue(int val) {
    try {
      if (DEBUG) Log.d(TAG, "Setting the value to " + val);
      return this.service.setValue(val);
    } catch (Exception e) {
      throw new RuntimeException("Failed to set the value", e);
    }
  }
}
