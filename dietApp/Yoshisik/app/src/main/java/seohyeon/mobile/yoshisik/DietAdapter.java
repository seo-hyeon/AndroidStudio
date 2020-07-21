package seohyeon.mobile.yoshisik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DietAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<DietData> myDataList;
    private LayoutInflater layoutInflater;

    public DietAdapter(Context context, int layout, ArrayList<DietData> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        if (view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
        }

        TextView week = view.findViewById(R.id.week);
        TextView breakfast = view.findViewById(R.id.breakfast);
        TextView lunch = view.findViewById(R.id.lunch);
        TextView dinner = view.findViewById(R.id.dinner);

        switch (i) {
            case 0:
                week.setText("월");
                view.setBackgroundColor(context.getResources().getColor(R.color.pink));
                break;
            case 1:
                week.setText("화");
                view.setBackgroundColor(context.getResources().getColor(R.color.purple));
                break;
            case 2:
                week.setText("수");
                view.setBackgroundColor(context.getResources().getColor(R.color.sky));
                break;
            case 3:
                week.setText("목");
                view.setBackgroundColor(context.getResources().getColor(R.color.pink));
                break;
            case 4:
                week.setText("금");
                view.setBackgroundColor(context.getResources().getColor(R.color.purple));
                break;
            case 5:
                week.setText("토");
                view.setBackgroundColor(context.getResources().getColor(R.color.sky));
                break;
            default:
                week.setText("일");
                view.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                break;

        }
        breakfast.setText(String.valueOf(myDataList.get(position).getBreakfast()));
        lunch.setText(String.valueOf(myDataList.get(position).getLunch()));
        dinner.setText(String.valueOf(myDataList.get(position).getDinner()));

        return view;
    }
}
