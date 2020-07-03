package dduwcom.mobile.finalreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    final static String TAG = "BookDBHelper";
    private Context context;
    private int layout;
    private ArrayList<BooKData> myDataList;
    private LayoutInflater layoutInflater;

    public BookAdapter(Context context, int layout, ArrayList<BooKData> myDataList) {
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;

        if (view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
        }

        TextView title = view.findViewById(R.id.title);
        TextView author = view.findViewById(R.id.author);
        TextView summary = view.findViewById(R.id.summary);
        ImageView image = view.findViewById(R.id.bookImage);
        ImageView star1 = view.findViewById(R.id.star1);
        ImageView star2 = view.findViewById(R.id.star2);
        ImageView star3 = view.findViewById(R.id.star3);
        ImageView star4 = view.findViewById(R.id.star4);
        ImageView star5 = view.findViewById(R.id.star5);

        title.setText(String.valueOf(myDataList.get(position).getTitle()));
        author.setText(String.valueOf(myDataList.get(position).getAuthor()));
        summary.setText(String.valueOf(myDataList.get(position).getSummary()));

        if (myDataList.get(position).getIsIm().equals("TRUE")) {
            image.setImageURI(myDataList.get(position).getImageUri());
        }

        switch (myDataList.get(position).getGrade()) {
            case 0:
                star1.setVisibility(View.INVISIBLE);
            case 1:
                star2.setVisibility(View.INVISIBLE);
            case 2:
                star3.setVisibility(View.INVISIBLE);
            case 3:
                star4.setVisibility(View.INVISIBLE);
            case 4:
                star5.setVisibility(View.INVISIBLE);
                break;
            case 5:
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        switch (myDataList.get(position).getColor()) {
            case "pink":
                view.setBackgroundColor(context.getResources().getColor(R.color.pink));
                break;
            case "purple":
                view.setBackgroundColor(context.getResources().getColor(R.color.purple));
                break;
            case "sky":
                view.setBackgroundColor(context.getResources().getColor(R.color.sky));
                break;
            case "orange":
                view.setBackgroundColor(context.getResources().getColor(R.color.orange));
                break;
            case "red":
                view.setBackgroundColor(context.getResources().getColor(R.color.red));
                break;
            case "green":
                view.setBackgroundColor(context.getResources().getColor(R.color.green));
                break;
            case "gray":
                view.setBackgroundColor(context.getResources().getColor(R.color.gray));
                break;
            case "blue":
                view.setBackgroundColor(context.getResources().getColor(R.color.blue));
                break;
            case "yellow":
                view.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                break;
            case "empty":
                break;
        }

        return view;
    }
}
