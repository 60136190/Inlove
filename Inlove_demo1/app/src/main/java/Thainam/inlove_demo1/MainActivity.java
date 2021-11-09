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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import database.UserDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsername;
    private EditText edtAddress;
    private Button btnAddUser;
    private RecyclerView rcvUser;


    private UserAdapter userAdapter;
    private List<User> mListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        userAdapter = new UserAdapter();
        mListUser = new ArrayList<>();
        userAdapter.setData(mListUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        rcvUser.setAdapter(userAdapter);





        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("list",new Gson().toJson(mListUser));
                startActivity(intent);
            }
        });
    }
    private List<User> getmListUser(){
        List<User> list     = new ArrayList<>();
        for(int i = 0; i<20 ; i++){
            list.add(new User("Tile item"+i,"Contenitem" +i ));
        }
        return list;
    }


    private void initUi(){
        edtUsername = findViewById(R.id.edt_username1);
        edtAddress = findViewById(R.id.edt_address1);
        btnAddUser = findViewById(R.id.btn_add_user1);
        rcvUser = findViewById(R.id.rcv_user);

    }
    public void addUser() {
        String strUsername = edtUsername.getText().toString().trim();
        String strAddress = edtAddress.getText().toString().trim();
        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
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