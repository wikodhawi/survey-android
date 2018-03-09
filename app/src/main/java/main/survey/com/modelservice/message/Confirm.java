
package main.survey.com.modelservice.message;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Confirm implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<Confirm> CREATOR = new Creator<Confirm>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Confirm createFromParcel(Parcel in) {
            return new Confirm(in);
        }

        public Confirm[] newArray(int size) {
            return (new Confirm[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3417188172515441327L;

    protected Confirm(Parcel in) {
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Confirm() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public Confirm(boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Confirm withStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Confirm withMessage(String message) {
        this.message = message;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
