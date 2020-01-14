package com.example.firebaseimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Load_Image_Activity extends AppCompatActivity {

    ImageView imageView;
    Button btn;
    private TextView url;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();
    private DatabaseReference childreference = databaseReference.child( "image" );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_load__image_ );

        url=findViewById( R.id.textId);
        imageView=findViewById( R.id.imgId );


    }

    @Override
    protected void onStart() {
        super.onStart();
        childreference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String message=dataSnapshot.getValue(String.class);
                url.setText( message );

                Picasso.get().load( message ).resize( 100,100 ).centerCrop().into( imageView );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
    }

