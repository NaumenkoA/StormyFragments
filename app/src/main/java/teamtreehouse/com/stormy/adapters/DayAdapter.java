package teamtreehouse.com.stormy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.ui.DailyFragment;
import teamtreehouse.com.stormy.weather.Day;

public class DayAdapter extends BaseAdapter implements View.OnClickListener {

    private DailyFragment.onDailyForecastSelectedInterface mListener;
    private Context mContext;
    private Day[] mDays;
    private int mIndex;

    public DayAdapter(Context context, Day[] days, DailyFragment.onDailyForecastSelectedInterface listener) {
        mContext = context;
        mDays = days;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; // we aren't going to use this. Tag items for easy reference
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        mIndex = position;
        if (convertView == null) {
            // brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.dayNameLabel);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = mDays[position];

        holder.iconImageView.setImageResource(day.getIconId());
        holder.temperatureLabel.setText(day.getTemperatureMax() + "");

        if (position == 0) {
            holder.dayLabel.setText("Today");
        }
        else {
            holder.dayLabel.setText(day.getDayOfTheWeek());
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {
        mListener.onDailyForecastSelected(mIndex);
    }

    private static class ViewHolder {
        ImageView iconImageView; // public by default
        TextView temperatureLabel;
        TextView dayLabel;
    }
}












