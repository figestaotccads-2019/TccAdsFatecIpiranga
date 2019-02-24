package com.figestaotcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button bpaction_sign_in_button;
    private Button bpaction_google_button;
    private Button bpaction_facebook_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();
        eventoClicks();
    }

    private void inicializaComponentes(){
        bpaction_sign_in_button = (Button) findViewById(R.id.action_sign_in_button);
        bpaction_google_button = (Button) findViewById(R.id.action_google_button);
        bpaction_facebook_button = (Button) findViewById(R.id.action_facebook_button);

    }

    private void eventoClicks(){
        bpaction_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ac = new Intent(getApplicationContext(),EmailPasswordActivity.class);
                startActivity(ac);
                            }
        });

    }

}


