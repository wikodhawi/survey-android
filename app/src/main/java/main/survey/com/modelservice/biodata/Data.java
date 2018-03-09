
package main.survey.com.modelservice.biodata;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("tbl_biodata")
    @Expose
    private List<TblBiodatum> tblBiodata = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6315006619591256239L;

    protected Data(Parcel in) {
        in.readList(this.tblBiodata, (TblBiodatum.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param tblBiodata
     */
    public Data(List<TblBiodatum> tblBiodata) {
        super();
        this.tblBiodata = tblBiodata;
    }

    public List<TblBiodatum> getTblBiodata() {
        return tblBiodata;
    }

    public void setTblBiodata(List<TblBiodatum> tblBiodata) {
        this.tblBiodata = tblBiodata;
    }

    public Data withTblBiodata(List<TblBiodatum> tblBiodata) {
        this.tblBiodata = tblBiodata;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(tblBiodata);
    }

    public int describeContents() {
        return  0;
    }

}
