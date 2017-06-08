package es.upv.master.pruebawear;

import android.app.Notification;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NotificationManagerCompat;

//**********************************
//adb -d forward tcp:5601 tcp:5601
//**********************************
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button wearButton = (Button) findViewById(R.id.boton1);
        wearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int notificacionId = 001;
                    Notification notificacion = new NotificationCompat.Builder(MainActivity.this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Título")
                            .setContentText("Notificación Android Wear")
                            .build();
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                    notificationManager.notify(notificacionId, notificacion);
                }
        });
    }
}
