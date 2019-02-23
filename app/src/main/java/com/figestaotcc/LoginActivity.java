package com.figestaotcc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity  {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usuariosReference;
    private EditText password;
    private EditText email;
    private Button email_sign_in_button;
    private Button recuperar_senha_button;
    private Button cadastrar_button;
    private TextView loginError;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        inicializaComponentes();
        eventoClicks();
        configuraDatabase();
    }


    private void inicializaComponentes(){
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        loginError = (TextView)findViewById(R.id.login_error);
        email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        recuperar_senha_button = (Button) findViewById(R.id.recuperar_senha_button);
        cadastrar_button = (Button) findViewById(R.id.cadastrar_button);

    }

    public static boolean isValidEmail(String email){
        if(email.contains("@")){
            return true;
        }
        return false;
    }

    public static void displayMessageToast(Context context, String displayMessage){
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }

    private void eventoClicks() {

        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredEmail = email.getText().toString();
                String enteredPassword = password.getText().toString();

                if(TextUtils.isEmpty(enteredEmail) || TextUtils.isEmpty(enteredPassword)){
                    displayMessageToast(LoginActivity.this, "Login fields must be filled");
                    return;
                }
                if(!isValidEmail(enteredEmail)){
                    displayMessageToast(LoginActivity.this, "Invalidate email entered");
                    return;
                }


                ((FirebaseApplication)getApplication()).loginAUser(LoginActivity.this, enteredEmail, enteredPassword, loginError);
            }
        });


        recuperar_senha_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vd) {
                Intent r = new Intent(getApplicationContext(), RecuperarActivity.class);
                startActivity(r);
            }
        });

        cadastrar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vb) {
                Intent c = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
                startActivity(c);
            }
        });


    }



    private void configuraDatabase (){
        firebaseDatabase = FirebaseDatabase.getInstance();
        usuariosReference = firebaseDatabase.getReference("users");
    }

    }


