package com.alohagoha.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {

    private static final String INSTAGRAM_URL = "https://www.instagram.com/evkingen";
    private static final String VK_URL = "https://www.vk.com/id1227900";
    private static final String TELEGRAM_URL = "https://t.me/alohagoha";
    private static final String EMAIL_ADDRESS = "zlock14@gmail.com";
    private static final String EMAIL_SUBJECT = "Feedback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final EditText et = findViewById(R.id.feedback_et);
        Button btn_send = findViewById(R.id.send_btn);
        btn_send.setOnClickListener(view -> {
            String emailText = et.getText().toString();
            sendMail(emailText);
        });

        ImageView iv_ref_1 = findViewById(R.id.iv_ref_1);
        iv_ref_1.setOnClickListener(view -> openURL(INSTAGRAM_URL));

        ImageView iv_ref_2 = findViewById(R.id.iv_ref_2);
        iv_ref_2.setOnClickListener(view -> openURL(VK_URL));

        ImageView iv_ref_3 = findViewById(R.id.iv_ref_3);
        iv_ref_3.setOnClickListener(view -> openURL(TELEGRAM_URL));

    }

    private void openURL(String URL) {
        Uri uri = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void sendMail(String emailText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {EMAIL_ADDRESS});
        intent.putExtra(Intent.EXTRA_SUBJECT,EMAIL_SUBJECT);
        intent.putExtra(Intent.EXTRA_TEXT,"Text1 " + emailText);
        startActivity(intent);
    }
}
