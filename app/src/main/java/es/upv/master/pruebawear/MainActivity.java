package es.upv.master.pruebawear;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

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
                String s = "MI CARRROOOOOOO  Texto largo de prueba, .... en un lugar de la mancha, de cuyo nombre no quiero acordarme ....";
                // Creamos intención pendiente
                //Intent intencionMapa = new Intent(MainActivity.this, MainActivity.class);
                Intent intencionMapa = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=universidad+politecnica+valencia"));
                PendingIntent intencionPendienteMapa = PendingIntent.getActivity(MainActivity.this, 0, intencionMapa, 0);
                Intent intencionLlamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:555123456"));
                PendingIntent intencionPendienteLlamar = PendingIntent.getActivity(MainActivity.this, 0, intencionLlamar, 0);

                int notificacionId = 001;
                NotificationCompat.Action accion = new NotificationCompat.Action.Builder(R.mipmap.ic_action_call, "llamar Wear", intencionPendienteLlamar).build();
                List<NotificationCompat.Action> listaAcciones = new ArrayList<NotificationCompat.Action>();
                listaAcciones.add(accion);
                listaAcciones.add(new NotificationCompat.Action(R.mipmap.ic_action_locate, "Ver mapa", intencionPendienteMapa));

                NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender().
                        setHintHideIcon(true).
                        setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.escudo_upv))
                        .addActions(listaAcciones);

                Notification notificacion = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Título")
                        //.setContentText("Notificación Android Wear")
                        .setContentText(Html.fromHtml("<b>Notificación</b> <u>Android <i>Wear</i></u>"))
                        // .setContentIntent(intencionPendienteMapa)
                        .addAction(R.mipmap.ic_action_call, "llamar", intencionPendienteLlamar)
                        .extend(new NotificationCompat.WearableExtender().addActions(listaAcciones))
                       // .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.escudo_upv))
                        .extend(wearableExtender)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(s + s))
                        .build();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificacionId, notificacion);
            }
        });
    }
}
