package main.survey.com.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import main.survey.com.model.OrderModel;
import main.survey.com.modelservice.biodata.TblBiodata;
import main.survey.com.modelservice.biodata.TblBiodatum;
import main.survey.com.survey.R;

/**
 * Created by user on 1/5/2018.
 */


public class ListOrderAdapter extends ArrayAdapter<TblBiodatum> {

    Context context;
    List<TblBiodatum> lstorder = new ArrayList<TblBiodatum>();

    public ListOrderAdapter(@NonNull Context context, int resource, @NonNull List<TblBiodatum> objects) {
        super(context, resource, objects);

            this.context = context;
            lstorder = objects;
        }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        listItem = LayoutInflater.from(context).inflate(R.layout.listorder,parent,false);
        TblBiodatum tblBiodatum=lstorder.get(position);
//            OrderModel order = lstorder.get(position);

        TextView txtNomor = (TextView) listItem.findViewById(R.id.nomor);
        txtNomor.setText(position+1+" "+tblBiodatum.getId()+"");

        ImageView image = (ImageView) listItem.findViewById(R.id.imageicon);
        image.setImageResource(R.drawable.text_icon);

        TextView txtNama = (TextView) listItem.findViewById(R.id.textView_name);
        txtNama.setText(tblBiodatum.getNama());

        TextView txtAlamat = (TextView) listItem.findViewById(R.id.textView_alamat);
        txtAlamat.setText(tblBiodatum.getAlamat());
        /*
        if(position%2==1)
        {
            if(listItem ==null){
                listItem = LayoutInflater.from(context).inflate(R.layout.listorder,parent,false);

            }
            TblBiodatum tblBiodatum=lstorder.get(position);
//            OrderModel order = lstorder.get(position);

            ImageView image = (ImageView) listItem.findViewById(R.id.imageicon);
            image.setImageResource(R.drawable.text_icon);

            TextView txtNama = (TextView) listItem.findViewById(R.id.textView_name);
            txtNama.setText(tblBiodatum.getNama());

            TextView txtAlamat = (TextView) listItem.findViewById(R.id.textView_alamat);
            txtAlamat.setText(tblBiodatum.getAlamat());


        }
        else
        {
            if(listItem ==null){
                listItem = LayoutInflater.from(context).inflate(R.layout.listorder2,parent,false);

            }

            TblBiodatum tblBiodatum=lstorder.get(position);
//            OrderModel order = lstorder.get(position);

            ImageView image = (ImageView) listItem.findViewById(R.id.imageicon2);
            image.setImageResource(R.drawable.text_icon);

            TextView txtNama = (TextView) listItem.findViewById(R.id.textView_name2);
            txtNama.setText(tblBiodatum.getNama());

            TextView txtAlamat = (TextView) listItem.findViewById(R.id.textView_alamat2);
            txtAlamat.setText(tblBiodatum.getAlamat());
        }
        */


        return listItem;
    }
}
