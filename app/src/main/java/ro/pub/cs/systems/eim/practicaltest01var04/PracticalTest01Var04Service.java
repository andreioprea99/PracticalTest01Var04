package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var04Service extends Service {
    public PracticalTest01Var04Service() {
    }

    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICE_ON", "A pornit");
        String name = intent.getStringExtra(Constants.nameKey);
        String group = intent.getStringExtra(Constants.groupKey);
        processingThread = new ProcessingThread(this, name, group);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}