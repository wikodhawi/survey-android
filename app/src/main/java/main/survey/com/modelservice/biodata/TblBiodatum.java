
package main.survey.com.modelservice.biodata;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblBiodatum implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("jeniskelamin")
    @Expose
    private String jeniskelamin;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    public final static Creator<TblBiodatum> CREATOR = new Creator<TblBiodatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TblBiodatum createFromParcel(Parcel in) {
            return new TblBiodatum(in);
        }

        public TblBiodatum[] newArray(int size) {
            return (new TblBiodatum[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4655365719365622786L;

    protected TblBiodatum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.pekerjaan = ((String) in.readValue((String.class.getClassLoader())));
        this.jeniskelamin = ((String) in.readValue((String.class.getClassLoader())));
        this.keterangan = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public TblBiodatum() {
    }

    /**
     * 
     * @param id
     * @param keterangan
     * @param jeniskelamin
     * @param nama
     * @param alamat
     * @param pekerjaan
     */
    public TblBiodatum(String id, String nama, String alamat, String pekerjaan, String jeniskelamin, String keterangan) {
        super();
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.jeniskelamin = jeniskelamin;
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TblBiodatum withId(String id) {
        this.id = id;
        return this;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public TblBiodatum withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public TblBiodatum withAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public TblBiodatum withPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
        return this;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public TblBiodatum withJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
        return this;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public TblBiodatum withKeterangan(String keterangan) {
        this.keterangan = keterangan;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
        dest.writeValue(alamat);
        dest.writeValue(pekerjaan);
        dest.writeValue(jeniskelamin);
        dest.writeValue(keterangan);
    }

    public int describeContents() {
        return  0;
    }

}
