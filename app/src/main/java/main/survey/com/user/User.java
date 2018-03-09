package main.survey.com.user;

/**
 * Created by user on 08/03/2018.
 */

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Serializable, Parcelable
{

    @SerializedName("username")
    @Expose
    private List<String> username = null;
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4421774546858352118L;

    protected User(Parcel in) {
        in.readList(this.username, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param username
     */
    public User(List<String> username) {
        super();
        this.username = username;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public User withUsername(List<String> username) {
        this.username = username;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(username);
    }

    public int describeContents() {
        return  0;
    }

}