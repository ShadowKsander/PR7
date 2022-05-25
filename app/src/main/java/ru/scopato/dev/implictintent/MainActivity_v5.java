package ru.scopato.dev.implictintent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity_v5 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("dgis://");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri); // Проверяет, установлено ли хотя бы одно приложение, способное выполнить это действие.
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) { //Если приложение установлено — запускаем его
            startActivity(intent);
        } else { // Если не установлено — переходим в Google Play.
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=ru.dublgis.dgismobile"));
            startActivity(intent);
        }
    }
}