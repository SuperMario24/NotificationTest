package com.example.saber.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSendNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendNotice = (Button) findViewById(R.id.btn_send_notice);

        btnSendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //点击通知之后跳到NotificationActivity
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                /**
                 * 包装意图
                 * 第一个参数为contex，
                 * 第二个通常为0
                 * 第三个为intent
                 * 第四个通常为0
                 */
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Notification notification = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))

                        //设置PendingIntent
                        .setContentIntent(pi)

                        //点击之后自动取消
                        .setAutoCancel(true)

                        //手机振动1秒 停一秒 再振动一秒
                        .setVibrate( new long[]{0,1000,1000,1000})

                        //提示文字很长时设置的style
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("this is very long text"))

                        //设置大图片
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)))

                        //设置重要程度 MIN LOW HIGH MAX DEFAULT
                        .setPriority(NotificationCompat.PRIORITY_MAX)

                        .build();
                //发送通知
                manager.notify(1,notification);

            }
        });



    }
}
