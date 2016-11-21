package com.example.radek.repositories;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button githubButton = (Button) findViewById(R.id.btn_github);
        githubButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"));
                startActivity(githubIntent);
            }
        });
    }
}
