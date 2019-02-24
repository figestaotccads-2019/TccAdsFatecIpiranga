package com.figestaotcc;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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


import static com.figestaotcc.R.layout.activity_cadastro_usuarios;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private static final String TAG = CadastroUsuarioActivity.class.getSimpleName();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usuariosReference;

    private EditText nameEditText;
    private EditText confirmpasswordEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registrar_button;
    private TextView loginError;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(activity_cadastro_usuarios);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }


        inicializaComponentes();
        eventoClicks();

    }

    private void inicializaComponentes(){
        mAuth = ((FirebaseApplication)getApplication()).getFirebaseAuth();
        ((FirebaseApplication)getApplication()).checkUserLogin(CadastroUsuarioActivity.this);

        loginError = (TextView)findViewById(R.id.login_error);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        registrar_button = (Button) findViewById(R.id.registrar_button);
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


    private void eventoClicks(){


        registrar_button = (Button) findViewById(R.id.registrar_button);
        registrar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredEmail = emailEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                if(TextUtils.isEmpty(enteredEmail) || TextUtils.isEmpty(enteredPassword)){
                    displayMessageToast(CadastroUsuarioActivity.this, "Login fields must be filled");
                    return;
                }
                if(!isValidEmail(enteredEmail)){
                    displayMessageToast(CadastroUsuarioActivity.this, "Invalidate email entered");
                    return;
                }

                ((FirebaseApplication)getApplication()).createNewUser(CadastroUsuarioActivity.this, enteredEmail, enteredPassword, loginError);
            }
        });
    }



}





