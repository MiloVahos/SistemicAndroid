package com.evilgeniuses.sistemicpublications;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IniciarSesionActivity extends AppCompatActivity {

    private EditText    EEmail;
    private EditText    EPass;
    private Button      BIniciarSesion;
    private Button      BCerrarSesion;

    private String  Email;
    private String  Password;

    private FirebaseAuth mAuth;
    private FirebaseUser User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        mAuth           =   FirebaseAuth.getInstance();

        EEmail          =   findViewById(R.id.EEmail);
        EPass           =   findViewById(R.id.EPassword);
        BIniciarSesion  =   findViewById(R.id.BIniciarSesion);
        BCerrarSesion   =   findViewById(R.id.BCerrarSesion);

        BIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email       =   EEmail.getText().toString().trim();
                Password    =   EPass.getText().toString().trim();

                if (!TextUtils.isEmpty(Email)   &&  !TextUtils.isEmpty(Password)){
                    mAuth.signInWithEmailAndPassword(Email,Password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(IniciarSesionActivity.this,
                                            R.string.Bienvenido,
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent   =   new Intent(IniciarSesionActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                }else{
                    Toast.makeText(IniciarSesionActivity.this,R.string.CamposVacios,Toast.LENGTH_SHORT).show();
                }

            }
        });

        BCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(IniciarSesionActivity.this);
                builder.setMessage(R.string.ConfirmarCS)
                        .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseAuth.getInstance().signOut();
                                Intent i = new Intent(IniciarSesionActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }
                        }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                dialog.dismiss();
                            }
                });
                builder.show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        User = mAuth.getCurrentUser();
        if (User !=  null){
            BCerrarSesion.setVisibility(View.VISIBLE);
            BIniciarSesion.setVisibility(View.GONE);
            EEmail.setInputType(InputType.TYPE_NULL);
            EPass.setInputType(InputType.TYPE_NULL);
        }else{
            BCerrarSesion.setVisibility(View.GONE);
            BIniciarSesion.setVisibility(View.VISIBLE);
            EEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            EPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

}
