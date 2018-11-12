package com.alohagoha.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AboutAppActivity extends AppCompatActivity {


    private static final String INSTAGRAM_URL = "https://www.instagram.com/evkingen";
    private static final String VK_URL = "https://www.vk.com/id1227900";
    private static final String TELEGRAM_URL = "https://t.me/alohagoha";
    private static final String EMAIL_ADDRESS = "zlock14@gmail.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        final EditText feedbackEditText = findViewById(R.id.et_feedback);

        final Button sendButton = findViewById(R.id.btn_send);
        sendButton.setOnClickListener(view -> {
            String emailText = feedbackEditText.getText().toString();
            sendMail(emailText);
        });

        final ImageView instagramImageView = findViewById(R.id.iv_my_instagram_link);
        instagramImageView.setOnClickListener(view -> openURL(INSTAGRAM_URL));

        final ImageView vkImageView = findViewById(R.id.iv_my_vk_link);
        vkImageView.setOnClickListener(view -> openURL(VK_URL));

        final ImageView telegramImageView = findViewById(R.id.iv_my_telegram_link);
        telegramImageView.setOnClickListener(view -> openURL(TELEGRAM_URL));

    }

    private void openURL(String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void sendMail(String emailText) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse(String.format("mailto:%s", EMAIL_ADDRESS)))
                .putExtra(Intent.EXTRA_SUBJECT, R.string.feedback_email_subject)
                .putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_email_app, Toast.LENGTH_LONG).show();
        }
    }
}
