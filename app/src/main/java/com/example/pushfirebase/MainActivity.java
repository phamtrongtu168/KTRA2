package com.example.pushfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText edtID,edtName;
    private Button btnAdd,btnEdit;
    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private List<User> mListUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        getListUser();



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                int id = Integer.parseInt(edtID.getText().toString().trim());
                String name = edtName.getText().toString().trim();
                User user = new User(id,name);
                onClickAddUser(user);
                        }
        });

    }

    private void AnhXa() {
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);
        edtID = findViewById(R.id.edt_id);
        edtName= findViewById(R.id.edt_name);
        rcvUser= findViewById(R.id.rcv_user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this ,DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(dividerItemDecoration);
        mListUser = new ArrayList<>();
        userAdapter = new UserAdapter(mListUser);
        rcvUser.setAdapter(userAdapter);

    }

    private void onClickAddUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_user");
        String pathObject = String.valueOf(user.getId());
        myRef.child(pathObject).setValue(user);


    }
    private void getListUser(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_user");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(mListUser != null){
//                    mListUser.clear();
//                }
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    User user = dataSnapshot.getValue(User.class);
//                    mListUser.add(user);
//                }
//                userAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if(user != null){
                    mListUser.add(user);
                    userAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void clickAddAll(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_user");
        List<User> list = new ArrayList<>();
        list.add(new User(1,"Trong Tu 1"));
        list.add(new User(2,"Trong Tu 2"));
        list.add(new User(3,"Trong Tu 3"));
        myRef.setValue(list);


    }
    private  void readDataBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_user");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot  dataSnapshot1 : dataSnapshot.getChildren()){
                    String id = dataSnapshot1.getKey();
                    User user = dataSnapshot.getValue(User.class);



                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
}