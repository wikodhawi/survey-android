
package main.survey.com.modelservice;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProduk implements Serializable, Parcelable
{

    @SerializedName("produk")
    @Expose
    private List<Produk_> produk = null;
    public final static Parcelable.Creator<DataProduk> CREATOR = new Creator<DataProduk>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DataProduk createFromParcel(Parcel in) {
            return new DataProduk(in);
        }

        public DataProduk[] newArray(int size) {
            return (new DataProduk[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7454341750468555967L;

    protected DataProduk(Parcel in) {
        in.readList(this.produk, (main.survey.com.modelservice.Produk_.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public DataProduk() {
    }

    /**
     *
     * @param produk
     */
    public DataProduk(List<Produk_> produk) {
        super();
        this.produk = produk;
    }

    public List<Produk_> getProduk() {
        return produk;
    }

    public void setProduk(List<Produk_> produk) {
        this.produk = produk;
    }

    public DataProduk withProduk(List<Produk_> produk) {
        this.produk = produk;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(produk);
    }

    public int describeContents() {
        return  0;
    }

}
