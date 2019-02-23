package com.figestaotcc;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseDatabase {

    private static final String TAG = FirebaseDatabase.class.getSimpleName();

    private DatabaseReference databaseReference;

    public FirebaseDatabase(){
        databaseReference = com.google.firebase.database.FirebaseDatabase.getInstance().getReference();
    }

    public void createUserInFirebaseDatabase(String userId, Usuario firebaseUserEntity){
        Map<String, Usuario> user = new HashMap<String, Usuario>();
        user.put(userId, firebaseUserEntity);
        databaseReference.child("users").setValue(user);
    }

    public void isUserKeyExist(final String chave, final Context context, final RecyclerView recyclerView){
        databaseReference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("User login 1 " + dataSnapshot.getKey() + " " + dataSnapshot.getValue());
                List<PerfilUsuario> userData = adapterSourceData(dataSnapshot, chave);
                System.out.println("User login Size " + userData.size());
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter((Activity)context, userData);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                List<PerfilUsuario> userData = adapterSourceData(dataSnapshot, chave);
                System.out.println("User login Size " + userData.size());
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter((Activity)context, userData);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private List<PerfilUsuario> adapterSourceData(DataSnapshot dataSnapshot, String chave){
        List<PerfilUsuario> allUserData = new ArrayList<PerfilUsuario>();
        if(dataSnapshot.getKey().equals(chave)){
            Usuario userInformation = dataSnapshot.getValue(Usuario.class);
            allUserData.add(new PerfilUsuario(Usuario.NAME, userInformation.getName()));
            allUserData.add(new PerfilUsuario(Usuario.EMAIL, userInformation.getEmail()));
            allUserData.add(new PerfilUsuario(Usuario.PASSWORD, userInformation.getPassword()));
            allUserData.add(new PerfilUsuario(Usuario.CONFIRMPASSWORD, userInformation.getConfirmPassword()));

        }
        return allUserData;
    }
}
