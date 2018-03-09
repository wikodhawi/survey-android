package main.survey.com.survey;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.survey.com.adapter.ListOrderAdapter;
import main.survey.com.model.OrderModel;
import main.survey.com.modelservice.biodata.TblBiodata;
import main.survey.com.modelservice.biodata.TblBiodatum;

public class ListOrder extends AppCompatActivity {

    Button btnNewEntry,btnCari;
    ListView listOrder;
    ListOrderAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        setTitle("List Order");

        btnNewEntry = (Button)findViewById(R.id.btnNewEntry);

        btnCari = (Button)findViewById(R.id.btnSearch);
        listOrder = (ListView)findViewById(R.id.lstOrder);

//        setupList();
        new SimpleTask().execute();

        btnNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final boolean running=true;
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        int i=1;
//                        Log.d("text",i+" thread masih jalan");
//                        i++;
//                    }
//                }).start();

                Intent intent = new Intent(ListOrder.this,EntryActivity.class);
                startActivity(intent);

            }
        });


//        listOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Intent intent = new Intent(ListOrder.this,EntryActivity.class);
//                intent.putExtra("from","view");
//                startActivity(intent);
//
//            }
//        });

        listOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                HttpGet post = new HttpGet("http://192.168.3.232/cicool/api/tbl_biodata/all");

                List<TblBiodatum> nameValuePairs = new ArrayList<TblBiodatum>(1);
//                nameValuePairs.add(new BasicNameValuePair("username",
//                        txtUsername.getText().toString()));
//                nameValuePairs.add(new BasicNameValuePair("password",
//                        txtPassword.getText().toString()));
//                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));


                post.addHeader("X-Api-Key" , "218B41B886DCFC41F90A15A8354080EC");

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
            Log.d("result",result+" hasil");

            return result;
        }

        protected void onPostExecute(String jsonString)  {
            createData(jsonString);
//            Gson gson = new GsonBuilder().create();
//            TypeToken<List<DataBiodata>> token = new TypeToken<List<DataBiodata>>(){};
//            List<DataBiodata> datas = gson.fromJson(jsonString, token.getType());
//            DataBiodata r = gson.fromJson(jsonString, DataBiodata.class);
//            Gson gson = new GsonBuilder().create();
//            TypeToken<List<TblBiodatum>> token = new TypeToken<List<TblBiodatum>>(){};
//            List<TblBiodatum> datas = gson.fromJson(jsonString, token.getType());
//            TblBiodatum tblBiodatum=datas.get(1);
//            Log.d("alamat1",tblBiodatum.getAlamat());
            //            for(TblBiodatum tblBiodatum: datas)
//            {
//                Log.d("alamat",tblBiodatum.getAlamat());
//
//            }

//             Dismiss ProgressBar
//            Gson gson = new GsonBuilder().create();
//            TblBiodatum r = gson.fromJson(jsonString, TblBiodatum.class);
//            Log.d("alamat", r.getAlamat()+" alamat");
//            authentication(jsonString);
//            if (jsonString!=null && jsonString!="") {
//                if (authentication(jsonString)) {
//                    Intent intent = new Intent(ListOrder.this, MenuActivity.class);
//                    startActivity(intent);
//
//                }
//            }

        }

        private boolean authentication(String jsonString) {
//            Gson gson = new Gson();
//            Produk_ auth = gson.fromJson(jsonString, Produk_.class);
//            Log.d("productname", auth.getProdukName());

//            DataProduk posts = auth.get;



//            if (auth.getToken() !=null){
//                return true;
//            }else{
//                Toast.makeText(getApplicationContext(),auth.getMessage(),Toast.LENGTH_LONG).show();
//                return false;
//            }
            return true;

        }

//        List<TblBiodatum> tblBiodata;
//
//        private void createData(String s)
//        {
//            Gson gson=new Gson();
//            TblBiodata dataBiodata=gson.fromJosn(s,TblBiodata.class);
//            tblBiodata=dataBiodata.getData().get
//
//        }

    }

//    OrderModel order;



    public void createData(String jsonString)
    {
        Gson gson = new Gson();
        TblBiodata tblBiodata=gson.fromJson(jsonString,TblBiodata.class);
        setupList(tblBiodata.getData().getTblBiodata());


    }

    private void setupList(List<TblBiodatum> tblBiodata){

//        List<OrderModel> penampungOrder=new ArrayList<OrderModel>();
//
//        order=new OrderModel("Dewa", "Pakuan", "1", "1", "Ok Deh");
//        penampungOrder.add(order);
//        order=new OrderModel("Toel", "Jonggol", "1", "1", "Ok Deh");
//        penampungOrder.add(order);
//        order=new OrderModel("Rudi", "Ceger", "1", "1", "Ok Deh");
//        penampungOrder.add(order);



        ListOrderAdapter adapter=new ListOrderAdapter(ListOrder.this, 0, tblBiodata);
        listOrder.setAdapter(adapter);


    }



    @Override
    protected void onResume() {
        super.onResume();
//        setupList();
    }
}
