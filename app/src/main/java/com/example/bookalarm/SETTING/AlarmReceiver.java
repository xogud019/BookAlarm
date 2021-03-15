package com.example.bookalarm.SETTING;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

    public class AlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent notificationIntent = new Intent(context, Setting.class);

            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingI = PendingIntent.getActivity(context, 0,
                    notificationIntent, 0);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                //builder.setSmallIcon(R.drawable.ic_launcher_foreground); /


                String channelName ="매일 알람 채널";
                String description = "매일 정해진 시간에 알람합니다.";
                int importance = NotificationManager.IMPORTANCE_HIGH;

                NotificationChannel channel = new NotificationChannel("default", channelName, importance);
                channel.setDescription(description);

                if (notificationManager != null) notificationManager.createNotificationChannel(channel);

            }
            //else builder.setSmallIcon(R.mipmap.ic_launcher);


            builder.setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())

                    .setTicker("{Time to watch some cool stuff!}")
                    .setContentTitle("상태바 드래그시 보이는 타이틀")
                    .setContentText("상태바 드래그시 보이는 서브타이틀")
                    .setContentInfo("INFO")
                    .setContentIntent(pendingI);

            if (notificationManager != null) {

                notificationManager.notify(1234, builder.build());

                Calendar nextNotifyTime = Calendar.getInstance();

                nextNotifyTime.add(Calendar.DATE, 1);

                SharedPreferences.Editor editor = context.getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                editor.putLong("nextNotifyTime", nextNotifyTime.getTimeInMillis());
                editor.apply();

                Date currentDateTime = nextNotifyTime.getTime();
                String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
                Toast.makeText(context.getApplicationContext(),"다음 알람은 " + date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();
            }
        }
    }

