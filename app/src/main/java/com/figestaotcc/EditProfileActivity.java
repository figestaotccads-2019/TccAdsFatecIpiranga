package com.figestaotcc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.figestaotcc.FirebaseDatabase;
import com.figestaotcc.Usuario;
import com.figestaotcc.R;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = EditProfileActivity.class.getSimpleName();

    private EditText editProfileName;

    private EditText editProfilePassword;

    private EditText editProfileConfirmPassword;

    private FirebaseAuth.AuthStateListener authStateListener;

    public static void displayMessageToast(Context context, String displayMessage){
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setTitle("Edit Profile Information");


        editProfileName = (EditText)findViewById(R.id.profile_name);
        editProfilePassword = (EditText)findViewById(R.id.profile_password);
        editProfileConfirmPassword = (EditText)findViewById(R.id.profile_confirm_password);


        Button saveEditButton = (Button)findViewById(R.id.save_edit_button);
        saveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profileName = editProfileName.getText().toString();
                String profilePassword = editProfilePassword.getText().toString();
                String profileConfirmPassword = editProfileConfirmPassword.getText().toString();


                // update the user profile information in Firebase database.
                if(TextUtils.isEmpty(profileName) || TextUtils.isEmpty(profilePassword) || TextUtils.isEmpty(profileConfirmPassword)){
                    displayMessageToast(EditProfileActivity.this, "All fields must be filled");
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null) {
                    Intent firebaseUserIntent = new Intent(EditProfileActivity.this, LoginActivity.class);
                    startActivity(firebaseUserIntent);
                    finish();
                } else {
                    String userId = user.getProviderId();
                    String chave = user.getUid();
                    String profileEmail = user.getEmail();

                    Usuario usuario = new Usuario (chave, profileName, profileEmail, profilePassword);
                    FirebaseDatabase firebaseDatabase = new FirebaseDatabase();
                    firebaseDatabase.createUserInFirebaseDatabase(chave, usuario);

                    editProfileName.setText("");
                    editProfilePassword.setText("");
                    editProfileConfirmPassword.setText("");

                }
            }
        });
    }
}
