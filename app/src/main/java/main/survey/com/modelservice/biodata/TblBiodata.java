
package main.survey.com.modelservice.biodata;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblBiodata implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("total")
    @Expose
    private int total;
    public final static Creator<TblBiodata> CREATOR = new Creator<TblBiodata>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TblBiodata createFromParcel(Parcel in) {
            return new TblBiodata(in);
        }

        public TblBiodata[] newArray(int size) {
            return (new TblBiodata[size]);
        }

    }
    ;
    private final static long serialVersionUID = -248832111483840913L;

    protected TblBiodata(Parcel in) {
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.total = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public TblBiodata() {
    }

    /**
     * 
     * @param total
     * @param message
     * @param status
     * @param data
     */
    public TblBiodata(boolean status, String message, Data data, int total) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TblBiodata withStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TblBiodata withMessage(String message) {
        this.message = message;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public TblBiodata withData(Data data) {
        this.data = data;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public TblBiodata withTotal(int total) {
        this.total = total;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(data);
        dest.writeValue(total);
    }

    public int describeContents() {
        return  0;
    }

}
