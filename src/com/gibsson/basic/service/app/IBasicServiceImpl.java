package com.gibsson.basic.service.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Slog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.gibsson.basic.service.lib.IBasicService;

class IBasicServiceImpl extends IBasicService.Stub {
  private static final String TAG = "IBasicServiceImpl";
  private static final boolean DEBUG = true;
  private final Context context;
  private int value;

  IBasicServiceImpl(Context context) {
    this.context = context;
  }

  protected void finalize() throws Throwable {
    super.finalize();
  }

  public int getValue() {
    if (DEBUG) Slog.d(TAG, "Getting the value: " + value);
    return value;
  }

  public int setValue(int val) {
    if (DEBUG) Slog.d(TAG, "Setting the value to " + val);
    value = val + 42;
    return 0;
  }
}
