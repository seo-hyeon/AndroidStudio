package ddwu.mobile.finalproject.ma01_20180968;

import android.graphics.Bitmap;

public class YoutubeData {
    private long _id;
    private String id;
    private String title;
    private String description;
    private String thubnailsURL;
    private Bitmap thub;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThubnailsURL() {
        return thubnailsURL;
    }

    public void setThubnailsURL(String thubnailsURL) {
        this.thubnailsURL = thubnailsURL;
    }

    public Bitmap getThub() {
        return thub;
    }

    public void setThub(Bitmap thub) {
        this.thub = thub;
    }

    public String toString() {
        return title + " (" + description + ") ";
    }
}
