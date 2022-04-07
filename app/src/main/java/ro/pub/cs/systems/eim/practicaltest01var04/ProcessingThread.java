package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

    public class ProcessingThread extends Thread {

        private Context context = null;
        private boolean isRunning = true;

        private Random random = new Random();

        private String name;
        private String group;

        public ProcessingThread(Context context, String name, String group) {
            this.context = context;
            this.name = name;
            this.group = group;

        }

        @Override
        public void run() {
//            Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
            while (isRunning) {
                sendMessage();
                sleep();
            }
//            Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
        }

        private void sendMessage() {
            Intent intent = new Intent();
            intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
            intent.putExtra("mesaj", "Nume " + name + " grupa " + group);
            context.sendBroadcast(intent);
        }

        private void sleep() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

        public void stopThread() {
            isRunning = false;
        }
    }

