package com.uef.android_note_app.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uef.android_note_app.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CalendarAdapter extends ArrayAdapter<Date> {

    private final HashSet<Date> eventDays;

    private final LayoutInflater inflater;

    public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays) {
        super(context, R.layout.custom_calendar_day, days);
        this.eventDays = eventDays;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Date date = getItem(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR) - 1900;

        Date today = new Date();

        if (view == null)
            view = inflater.inflate(R.layout.custom_calendar_day, parent, false);

        view.setBackgroundResource(0);
        if (eventDays != null) {
            for (Date eventDate : eventDays) {
                if (eventDate.getDate() == day &&
                        eventDate.getMonth() == month &&
                        eventDate.getYear() == year) {
                    view.setBackgroundResource(R.drawable.reminder);
                    break;
                }
            }
        }

        ((TextView) view).setTypeface(null, Typeface.NORMAL);
        ((TextView) view).setTextColor(Color.WHITE);

        if (month != today.getMonth() || year != today.getYear()) {
            ((TextView) view).setTextColor(Color.rgb(65, 65, 65));
        } else if (day == today.getDate()) {
            ((TextView) view).setTypeface(null, Typeface.BOLD);
            ((TextView) view).setTextColor(Color.rgb(51, 51, 51));
            ((TextView) view).setBackgroundResource(R.drawable.rounded_button);
        }

        ((TextView) view).setText(String.valueOf(date.getDate()));

        return view;
    }

}
