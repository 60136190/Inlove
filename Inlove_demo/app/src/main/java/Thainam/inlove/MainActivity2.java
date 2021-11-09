package Thainam.inlove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DateSorter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import database.UserDatabase;

public class MainActivity2 extends AppCompatActivity {
     EditText edtUsername;
     EditText edtAddress;
     Button btnAddUser;
     RecyclerView rcvUser;
     UserAdapter userAdapter;
     List<User> mListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnAddUser = findViewById(R.id.btn_add);
        initUi();


        userAdapter = new UserAdapter();
        mListUser = new ArrayList<>();
        userAdapter.setData(mListUser);
        Collections.sort(mListUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        rcvUser.setAdapter(userAdapter);


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
//                intent.putExtra("list",new Gson().toJson(mListUser));
                startActivity(intent);
            }
        });
    }
    private void initUi(){
        edtUsername = findViewById(R.id.edt_username1);
        edtAddress = findViewById(R.id.edt_address1);
        btnAddUser = findViewById(R.id.btn_add);
        rcvUser = findViewById(R.id.rcv_user);
    }
    public void addUser() {
            String strUsername = edtUsername.getText().toString().trim(); // change string to integer ............!
        String strAddress = edtAddress.getText().toString().trim();
        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            Toast.makeText(this,"Bạn chưa thêm kỷ niệm",Toast.LENGTH_LONG).show();
            mListUser =  UserDatabase.getInstance(this).userDAO().getListUser();
            return;
        }
        User user = new User(strUsername,strAddress);
        UserDatabase.getInstance(this).userDAO().insertUser(user);
//        Toast.makeText(this,"correct",Toast.LENGTH_LONG).show();
        edtAddress.setText("");
        edtUsername.setText("");

        hideSoftKeyboard();
        mListUser =  UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(mListUser);

    }
    public void hideSoftKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

}