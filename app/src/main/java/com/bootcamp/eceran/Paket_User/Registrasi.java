package com.bootcamp.eceran.Paket_User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bootcamp.eceran.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Date;

public class Registrasi extends AppCompatActivity implements View.OnClickListener {

    EditText name,email,password, noTlp;
    Button mRegisterbtn;
    FirebaseAuth mAuth;
    DatabaseReference mdatabase;
    String Photo, Name,Email,Password, NoTelp;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        name = (EditText)findViewById(R.id.id_nama);
        noTlp = (EditText) findViewById(R.id.idnoHP);
        email = (EditText)findViewById(R.id.id_email);
        password = (EditText)findViewById(R.id.id_password);
        mRegisterbtn = (Button)findViewById(R.id.buttonRegister);

        // for authentication using FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();
        mRegisterbtn.setOnClickListener(this);
        mDialog = new ProgressDialog(this);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void onClick(View v) {
        if (v==mRegisterbtn){
            UserRegister();
        }
    }

    private void UserRegister() {
        Name = name.getText().toString().trim();
        NoTelp = noTlp.getText().toString().trim();
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();

        if (TextUtils.isEmpty(Name)){
            Toast.makeText(Registrasi.this, "Masukkan Nama", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Email)){
            Toast.makeText(Registrasi.this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(NoTelp)){
            Toast.makeText(Registrasi.this, "Masukkan No Telp", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Password)){
            Toast.makeText(Registrasi.this, "Masukkan Password", Toast.LENGTH_SHORT).show();
            return;
        }else if (Password.length()<6){
            Toast.makeText(Registrasi.this,"Masukkan Password Minimal 6 Karakter",Toast.LENGTH_SHORT).show();
            return;
        }
        mDialog.setMessage("Mohon Tunggu...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    sendEmailVerification();
                    mDialog.dismiss();
                    OnAuth(task.getResult().getUser());
                    mAuth.signOut();
                }else if (!task.isSuccessful()){
                    Toast.makeText(Registrasi.this,"Terjadi Kesalahan",Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
                else{
                    Toast.makeText(Registrasi.this,"Email Atau Password Sudah Ada",Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            }
        });
    }
    //Email verification code using FirebaseUser object and using isSucccessful()function.
    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Registrasi.this,"Sukses, Cek Email Anda Untuk Verifikasi",Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(Registrasi.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    private void uploadProfileImage(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final StorageReference mStorageReference = FirebaseStorage.getInstance()
                .getReference()
                .child( "Users" +
                        user.getUid()+
                        Photo.substring( Photo.lastIndexOf( "." ) ) );

        UploadTask uploadTask = ((StorageReference) mStorageReference).putFile( Uri.fromFile( new File( Photo ) ) );
        uploadTask.continueWithTask( new Continuation< UploadTask.TaskSnapshot, Task< Uri > >(){
            @Override
            public Task< Uri > then(@NonNull Task< UploadTask.TaskSnapshot > task ) throws Exception{
                if( !task.isSuccessful() )
                    throw task.getException();

                return mStorageReference.getDownloadUrl();
            }
        } ).addOnCompleteListener( new OnCompleteListener< Uri >(){
            @Override
            public void onComplete( @NonNull Task<Uri> task ){
                if( task.isSuccessful() ){
                    Photo = task.getResult().toString();
                    FirebaseDatabase.getInstance().getReference( "Users" )
                            .child( user.getUid() )
                            .child( "image" )
                            .setValue( Photo );
                }
            }
        } );
    }

    private void OnAuth(FirebaseUser user) {
        createAnewUser(user.getUid());
    }

    private void createAnewUser(String uid) {
        User user = BuildNewuser();
        mdatabase.child(uid).setValue(user);
    }


    private User BuildNewuser(){
        return new User(
                getDisplayName(),
                getPhoto(),
                getNoTelp(),
                getUserEmail(),
                getPassword(),
                new Date().getTime()
        );
    }

    public String getPhoto(){
        return Photo;
    }

    public String getDisplayName() {

        return Name;
    }

    public  String getNoTelp(){

        return NoTelp;
    }

    public String getUserEmail() {

        return Email;
    }

    public String getPassword(){

        return Password;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
