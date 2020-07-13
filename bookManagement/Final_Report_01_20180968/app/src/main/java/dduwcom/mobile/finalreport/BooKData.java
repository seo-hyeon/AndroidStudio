package dduwcom.mobile.finalreport;

import android.net.Uri;

public class BooKData {
    private long _id;
    private String isIm;
    private Uri imageUri;
    private String title;
    private String author;
    private String summary;
    private String content;
    private String experience;
    private String color;
    private int grade;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getIsIm() {
        return isIm;
    }

    public void setIm(String isIm) {
        this.isIm = isIm;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getColor() {
        return color;
    }

    public void setColor() {
        this.color = color;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public BooKData(long id, String isIm, Uri imageUri, String title, String author, String summary, String content, String experience, String color, int grade) {
        _id = id;
        this.isIm = isIm;
        this.imageUri = imageUri;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.content = content;
        this.experience = experience;
        this.color = color;
        this.grade = grade;
    }
}
