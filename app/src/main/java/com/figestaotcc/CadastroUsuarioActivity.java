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

        loginError = (TextView)findViewById(R.id.login_error);
        //nameEditText = (EditText) findViewById(R.id.nameEditText);
        //confirmpasswordEditText = (EditText) findViewById(R.id.confirmpasswordEditText);
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

        registrar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getEditableText().toString();
                String email = emailEditText.getEditableText().toString();
                String password = passwordEditText.getEditableText().toString();
                String confirmpassword = confirmpasswordEditText.getEditableText().toString();
                String chave = usuariosReference.push().getKey();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    displayMessageToast(CadastroUsuarioActivity.this, "Login fields must be filled");
                    return;
                }
                if(!isValidEmail(email)){
                    displayMessageToast(CadastroUsuarioActivity.this, "Invalidate email entered");
                    return;
                }

                Usuario usuario = new Usuario (name, email, password,confirmpassword);
                usuariosReference.child(chave).setValue(usuario);

                Toast.makeText(CadastroUsuarioActivity.this,
                        getString(R.string.usuario_cadastrado),
                        Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CadastroUsuarioActivity.this,
                        LoginActivity.class);
                startActivity(i);
                finish();

            }

        });
    }

    }





