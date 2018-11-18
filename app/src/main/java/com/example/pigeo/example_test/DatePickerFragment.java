package com.example.pigeo.example_test;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    public interface DatePickerInterface{
        void onDatePicked(Date date);
    }

    private DatePickerInterface client;

    public void attach(DatePickerInterface client){
        this.client = client;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.datepicker, null);

        CalendarView cv = v.findViewById(R.id.calendarView);
        cv.setDate(new Date().getTime() - (60*60*24*3000));
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date selected = new Date(year, month, dayOfMonth);
                client.onDatePicked(selected);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setPositiveButton("Ok", null);

        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.client = (DatePickerInterface) context;
        if( context instanceof DatePickerInterface) {
            this.client = (DatePickerInterface) context;
        }
        else{
            throw new RuntimeException("SVP, implementer DatePickerInterface");
        }
    }

}
