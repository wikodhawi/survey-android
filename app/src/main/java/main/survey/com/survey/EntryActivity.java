package main.survey.com.survey;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import main.survey.com.modelservice.message.Confirm;
import main.survey.com.survey.main.survey.com.utility.DataHelper;

public class EntryActivity extends AppCompatActivity {

    EditText txtNama, txtAla,txtAlamat;
    Spinner lstPekerjaan;
    Intent intent;
    String nama ="";
    String alamat ="";

    RadioGroup rg1;
    RadioButton rb1,rb2;
    EditText txtKeterangan;
    Button btnSubmit,btnCancel,btnFav;
    String from;

    DataHelper dbHelper;

    public void getCheckbox()
    {
        CheckBox a,b,c;
        List<CheckBox> list= new ArrayList<>();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        setTitle("Entry Survey");

        lstPekerjaan = (Spinner)findViewById(R.id.lst_pekerjaan);
        setupSpinner();

        txtNama = (EditText) findViewById(R.id.txtNama);
        txtAlamat = (EditText) findViewById(R.id.txtAlamat);
        txtKeterangan = (EditText)findViewById(R.id.txtKeterangan);

        rg1 = (RadioGroup)findViewById(R.id.radioGroup);

        String jenisKelamin;

        rb1 = (RadioButton)findViewById(R.id.rdPria);
        rb2 = (RadioButton)findViewById(R.id.rdWanita);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnCancel = (Button)findViewById(R.id.btnCancel);
   //     btnFav = (Button)findViewById(R.id.btnFav);

        intent = getIntent();
        if(intent !=null) {


        }




        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("nama",txtNama.getText().toString());
                Log.d("alamat",txtAlamat.getText().toString());
                Log.d("keterangan",txtKeterangan.getText().toString());
                Log.d("pekerjaan",lstPekerjaan.getSelectedItem().toString());
                int jk=rg1.getCheckedRadioButtonId();
                Log.d("jeniskelamin",jk+"");
                Log.d("all",txtNama.getText().toString()+" "+txtAlamat.getText().toString()+" "+txtKeterangan.getText().toString()+" "+lstPekerjaan.getSelectedItem().toString()+" "+jk);
                jk=jk%10;
                /*
                RadioButton x=(RadioButton) findViewById(rg1.getCheckedRadioButtonId());
                if(x==rb1)
                    jenisKelamin="0";
                else jenisKelamin="1";

                */
                new SimpleTask().execute();
            }
        });

    }


    public String getJenisKelamin()
    {
        if(rg1.getCheckedRadioButtonId()==Integer.parseInt(rb1.getText().toString()))
            return "0";
        else return "1";
    }

    private void setupSpinner(){

        List<String> list = new ArrayList<String>();
        list.add("Wiraswasta");
        list.add("PNS");
        list.add("Lain Lain");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lstPekerjaan.setAdapter(dataAdapter);



    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                HttpPost post = new HttpPost("http://192.168.3.232/cicool/api/tbl_biodata/add");

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("nama",
                        txtNama.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("alamat",
                        txtAlamat.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("pekerjaan",
                        lstPekerjaan.getSelectedItem().toString()));

                int jk=rg1.getCheckedRadioButtonId();
                jk=jk%10;
                String jenisKelamin;
                if(rg1.getCheckedRadioButtonId()==rb1.getId()) // nyamain radio button tuh pake idnya antara radiogroup dengan radiobuttonnya
                {
                    jenisKelamin="0";
                }
                else jenisKelamin="1";
                nameValuePairs.add(new BasicNameValuePair("jeniskelamin",
                        jenisKelamin));
                nameValuePairs.add(new BasicNameValuePair("keterangan",
                        txtKeterangan.getText().toString()));
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
                Gson gson = new Gson();
                Confirm status=gson.fromJson(jsonString,Confirm.class);
                Toast.makeText(getApplicationContext(), status.getMessage(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EntryActivity.this, ListOrder.class);
                startActivity(intent);


            }
        }


    }

    public void simpanDB(){

        dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into biodata(nama, alamat, pekerjaan, jeniskelamin, keterangan) values('" +
                txtNama.getText().toString()+"','"+
                txtAlamat.getText().toString() +"','" +
                String.valueOf(lstPekerjaan.getSelectedItemPosition())+"','"+
                String.valueOf(rg1.getCheckedRadioButtonId()) +"','" +
                txtKeterangan.getText().toString() + "')");
        Toast.makeText(getApplicationContext(),"Tersimpan di database",Toast.LENGTH_LONG).show();
        Cursor cursor = db.rawQuery("SELECT * FROM biodata",null);
        cursor.moveToFirst();

        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            Log.d("Test",cursor.getString(1).toString());
        }



    }

}
