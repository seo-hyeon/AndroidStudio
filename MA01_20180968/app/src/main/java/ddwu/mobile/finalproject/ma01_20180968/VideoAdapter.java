package ddwu.mobile.finalproject.ma01_20180968;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class VideoAdapter extends BaseAdapter {
    final static String TAG = "VideoAdapter";

    private Context context;
    private int layout;
    private ArrayList<YoutubeData> myDataList;
    private LayoutInflater layoutInflater;

    public VideoAdapter(Context context, int layout, ArrayList<YoutubeData> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return myDataList.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        Log.d(TAG, "position: " + i);
        Log.d(TAG, "title: " + myDataList.get(i).getTitle());

        if (view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
        }

        TextView title = view.findViewById(R.id.title);
        TextView describe = view.findViewById(R.id.describe);
        ImageView image = view.findViewById(R.id.VideoImage);

        title.setText(myDataList.get(i).getTitle());
        describe.setText(myDataList.get(i).getDescription());

        image.setImageBitmap(myDataList.get(i).getThub());

        return view;
    }
}
