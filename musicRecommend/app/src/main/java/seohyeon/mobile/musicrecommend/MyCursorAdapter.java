package seohyeon.mobile.musicrecommend;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
    LayoutInflater inflater;
    int layout;

    public MyCursorAdapter(Context context, int layout, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public View newView (Context context, Cursor cursor, ViewGroup parent) {
        View listItemLayout = inflater.inflate(layout, parent, false);
        return listItemLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvContactTitle = view.findViewById(R.id.mTitle);
        TextView tvContactSinger = view.findViewById(R.id.mSinger);
        TextView tvContactLength = view.findViewById(R.id.mLength);
        TextView tvContactReview = view.findViewById(R.id.mReview);

        tvContactTitle.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_TITLE)));
        tvContactSinger.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_SINGER)));
        tvContactLength.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_LENGTH)));
        tvContactReview.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_REVIEW)));
    }
}
