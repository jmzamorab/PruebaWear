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
    final static String MI_GRUPO_DE_NOTIFIC = "mi_grupo_de_notific";

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
                List<Notification> listaPaginas = new ArrayList<Notification>();
                listaAcciones.add(accion);
                listaAcciones.add(new NotificationCompat.Action(R.mipmap.ic_action_locate, "Ver mapa", intencionPendienteMapa));

                NotificationCompat.BigTextStyle segundaPg = new NotificationCompat.BigTextStyle();
                segundaPg.setBigContentTitle("Página 2").bigText("PAGINA 2;  Más texto.");
                // Creamos una notification para la segunda página Notification
                Notification notificacionPg2 = new NotificationCompat.Builder(MainActivity.this).setStyle(segundaPg).build();

                NotificationCompat.BigTextStyle terceraPg = new NotificationCompat.BigTextStyle();
                terceraPg.setBigContentTitle("Página 3").bigText("PAGINA 3;  Más texto aún III.");
                Notification notificacionPg3 = new NotificationCompat.Builder(MainActivity.this).setStyle(terceraPg).build();

                NotificationCompat.BigTextStyle cuartaPg = new NotificationCompat.BigTextStyle();
                cuartaPg.setBigContentTitle("Página 4").bigText("PAGINA 4;  Última página IV.");
                Notification notificacionPg4 = new NotificationCompat.Builder(MainActivity.this).setStyle(cuartaPg).build();

                listaPaginas.add(notificacionPg2);
                listaPaginas.add(notificacionPg3);
                listaPaginas.add(notificacionPg4);

                NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender().
                        setHintHideIcon(true).
                        setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.escudo_upv))
                        .addActions(listaAcciones)
                        .addPages(listaPaginas);
                //addPage(notificacionPg2);

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
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(s))
                        .setGroup(MI_GRUPO_DE_NOTIFIC)
                        .build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificacionId, notificacion);

                int idNotificacion2 = 002;
                Notification notificacion2 = new NotificationCompat.Builder(MainActivity.this).
                        setContentTitle("Nueva Conferencia").
                        setContentText("Los neutrinos").
                        setSmallIcon(R.mipmap.ic_action_mail_add).
                        setGroup(MI_GRUPO_DE_NOTIFIC).build();
                //notificationManager.notify(idNotificacion2, notificacion2);

                int idNotificacion3 = 003;
                Notification notificacion3 = new NotificationCompat.Builder(MainActivity.this).
                        setContentTitle("2 notificaciones UPV").
                        setSmallIcon(R.mipmap.ic_action_attach)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.escudo_upv)).
                         setStyle(new NotificationCompat.InboxStyle().addLine("Nueva Conferencia Los neutrinos").addLine("Nuevo curso Android Wear").setBigContentTitle("2 notificaciones UPV").setSummaryText("info@upv.es")).setNumber(2)
                        .setGroup(MI_GRUPO_DE_NOTIFIC).
                         setGroupSummary(true).build();
                notificationManager.notify(idNotificacion3, notificacion3);

            }
        });
    }
}
