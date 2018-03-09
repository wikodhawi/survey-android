package main.survey.com.survey;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.survey.com.modelservice.Data;
import main.survey.com.modelservice.Login;
import main.survey.com.survey.main.survey.com.utility.SharedPreferenceUtils;
import main.survey.com.user.User;

public class MainActivity extends AppCompatActivity {


    EditText txtUsername,txtPassword ;
    Button btnLogin,btnRegister;
    CheckBox rememberMe;

    SharedPreferenceUtils sharedPreferenceUtils=new SharedPreferenceUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");



        txtUsername = (EditText)findViewById(R.id.username);
        txtPassword = (EditText)findViewById(R.id.password);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);



        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);


//        sharedPreferenceUtils.setSetting(getApplicationContext(),"key",txtUsername.getText().toString());
//        //sharedPreferenceUtils.setSetting(getApplicationContext(),"key",txtUsername.getText().toString());
//        String username = SharedPreferenceUtils.getSetting(getApplicationContext(),"username","");
//
//        if (!username.equalsIgnoreCase("")){
//            txtUsername.setText(username);
//            rememberMe.setChecked(true);
//        }else {
//
//            txtUsername.setText("");
//        }
//
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();


        //males login
        txtUsername.setText("dewabrata");
        txtPassword.setText("1234567890");

        //rajin login
        /*
        String username = SharedPreferenceUtils.getSetting(getApplicationContext(),"username","");
        if(!username.equals(""))
        {
            rememberMe.setChecked(true);
            txtUsername.setText(username);
        }
        */

//
//        rememberMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                {
//                    if(rememberMe.isChecked())
//                    {
//                        String username = SharedPreferenceUtils.getSetting(getApplicationContext(),"username","");
//                        if (!username.equalsIgnoreCase("")){
//                            txtUsername.setText(username);
//                            rememberMe.setChecked(true);
//                        }else {
//
//                            txtUsername.setText("");
//                        }
//
//                    }
//
//
//                }
//
//            }
//        });
//        {
////            sharedPreferenceUtils.setSetting(getApplicationContext(),"key",txtUsername.getText().toString());
////            sharedPreferenceUtils.setSetting(getApplicationContext(),"key",txtUsername.getText().toString());
//
//            String username = SharedPreferenceUtils.getSetting(getApplicationContext(),"username","");
//            if (!username.equalsIgnoreCase("")){
//                txtUsername.setText(username);
//                rememberMe.setChecked(true);
//            }else {
//
//                txtUsername.setText("");
//            }
//
//        }
//        else
//        {
//            txtUsername.setText("");
//            rememberMe.setChecked(false);
//
//        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"Anda memasukan username:"+txtUsername.getText().toString(),Toast.LENGTH_LONG).show();
                //  showDialog();
                //  startActivity(new Intent(getApplicationContext(),MenuActivity.class));
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//
//                editor.putString("username", txtUsername.getText().toString());  // Saving string

// Save the changes in SharedPreferences
//                editor.commit(); // commit changes
                new SimpleTask().execute();

//                if(rememberMe.isChecked()==true)
//                {
//                    new SimpleTask().execute();
//                }
//                else
//                    new WithoutSaveUsername().execute();


            }
        });


    }

    private void createUser()
    {
        List<String> username=new ArrayList<>();
        username.add("Dewa");
        username.add("Brata");
        username.add("dewabrata");

        User user=new User();
        user.setUsername(username);

        Gson gson=new Gson();
//        Type type=new Typeoken<>();


        String json =gson.toJson(user);

        SharedPreferenceUtils.setSetting(getApplicationContext(),"username2", json);


    }

    public void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // menambahkan dialog
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        builder.setMessage("Halo Dialog")
                .setTitle("Dialog Test");

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private class ServiceTask extends  AsyncTask<String,Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }



    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                HttpPost post = new HttpPost("http://192.168.3.232/cicool/api/user/login");

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("username",
                        txtUsername.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("password",
                        txtPassword.getText().toString()));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));


                post.addHeader("X-Api-Key" , "59D3CFCA29DB8697C4962A36EEB653C8");




                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(post);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200 || statusCode ==406) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar
            if (jsonString!=null && jsonString!="") {
                if (authentication(jsonString)) {
                    SharedPreferenceUtils.setSetting(getApplicationContext(), "usernameLogin", txtUsername.getText().toString());
                    if(rememberMe.isChecked())
                        SharedPreferenceUtils.setSetting(getApplicationContext(), "username", txtUsername.getText().toString());
                    else
                        SharedPreferenceUtils.setSetting(getApplicationContext(), "username", "");
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);

                }
            }
        }

        private boolean authentication(String jsonString) {
            Gson gson = new Gson();
            Login auth = gson.fromJson(jsonString, Login.class);
            Data posts = auth.getData();


            if (auth.getToken() !=null){
                return true;
            }else{
                Toast.makeText(getApplicationContext(),auth.getMessage(),Toast.LENGTH_LONG).show();
                return false;
            }

        }

    }



}