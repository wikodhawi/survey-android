package main.survey.com.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import main.survey.com.PhotoFile;
import main.survey.com.survey.main.survey.com.utility.SharedPreferenceUtils;

public class MenuActivity extends AppCompatActivity {

    Button btnOrderMenu,btnOrderSent,btnLogout,btnCamera,cobaScroll;
    TextView txtUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Menu");


        String username = SharedPreferenceUtils.getSetting(getApplicationContext(),"usernameLogin","");

        btnOrderMenu = (Button)findViewById(R.id.btnMenuOrder);
        btnOrderSent = (Button)findViewById(R.id.btnMenuSend);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnCamera = (Button)findViewById(R.id.camera);
        cobaScroll=(Button)findViewById(R.id.cobaScroll);
        Log.d("usernameLogin",username);
        txtUsername=(TextView) findViewById(R.id.username);
        txtUsername.setText("Hello "+username);


        btnOrderMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ListOrder.class);
                startActivity(intent);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                 Intent intent = new Intent(MenuActivity.this,PhotoFile.class);
                 startActivity(intent);
             }
         }
        );

        cobaScroll.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,CobaScroll.class);
                startActivity(intent);
            }
                      }
        );

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
