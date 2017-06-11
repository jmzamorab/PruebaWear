package es.upv.master.pruebawear;

import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by padres on 12/06/2017.
 */

public class WearReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MainActivity.ACTION_DEMAND)) {
            String extras = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            Log.wtf("*** Notificaciones*** ", "Se recibe ACTION_DEMAND; extras = " + extras);
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            CharSequence reply = remoteInput.getCharSequence(MainActivity.EXTRA_RESPUESTA_POR_VOZ);
            Log.wtf("*** Notificaciones ***", "Respuesta dictada desde el wearable: " + reply);
        }
    }
}