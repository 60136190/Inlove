package Thainam.inlove_demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import database.UserDatabase;

public class MainActivity2 extends AppCompatActivity {
    private UserAdapter userAdapter;
    private RecyclerView rcvUser1;
    private List<User> mListUser;
    private Button change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String data = getIntent().getStringExtra("list");
        mListUser = new Gson().fromJson(data,new TypeToken<ArrayList<User>>(){}.getType());
        rcvUser1 = findViewById(R.id.rcv_user2);
        userAdapter = new UserAdapter();
        if (mListUser==null) mListUser = new ArrayList<>();
        mListUser =  UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(mListUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser1.setLayoutManager(linearLayoutManager);
        rcvUser1.setAdapter(userAdapter);
        change = findViewById(R.id.btn_change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyen = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(chuyen);
            }
        });

    }

}