package com.rememberme.rememberme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;

public class trip_calendar_Activity extends AppCompatActivity implements CalendarCellDecorator {
    boolean beforeClicked=false;
    String startDay;
    String endDay;
    Date startDate,endDate;
    List<Date> choiceDate;
    SimpleDateFormat sdf;
    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_calendar);


        Calendar nextMonth = Calendar.getInstance();
        Calendar beforeYear = Calendar.getInstance();
        beforeYear.add(Calendar.MONTH,-1);
        nextMonth.add(Calendar.MONTH, 2);

        final CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(beforeYear.getTime(), nextMonth.getTime())
                .withSelectedDate(today);




        calendar.init(beforeYear.getTime(), nextMonth.getTime())
                .inMode(RANGE);

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override

            public void onDateSelected(Date date) {

                Log.i("aaDates", calendar.getSelectedDates().toString());
                choiceDate = calendar.getSelectedDates();


                if(calendar.getSelectedDates().size() > 1){
                    AlertDialog.Builder alert = new AlertDialog.Builder(trip_calendar_Activity.this);
                    alert.setTitle("일정 저장");
                    alert.setMessage("일정을 저장하시겠습니까?");
                    alert.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
//                        negative, neu~
                    });

                    alert.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //setText btnstartdate;
                            //startDate, endDate는 서버로 보내고
                            //startDate를 tostring으로바꿔서
                            startDate = choiceDate.get(0);
                            endDate = choiceDate.get(choiceDate.size()-1);
                            sdf = new SimpleDateFormat("YYYY년 MM월 dd일", Locale.KOREA);
                            startDay = sdf.format(startDate);
                            endDay = sdf.format(endDate);


                            Bundle data = new Bundle();
                            data.putString("startDay", startDay);
                            data.putString("endDay", endDay);
                            // set MyFragment Arguments
                            AddTitleFragment myObj = new AddTitleFragment();
                            myObj.setArguments(data);
                            finish();
                            Intent mainIntent = new Intent(getApplicationContext(),AddTitleFragment.class);
                            startActivity(mainIntent);

                        }
                    });
                    alert.show();

                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
        calendar.setCellClickInterceptor(new CalendarPickerView.CellClickInterceptor() {
            @Override
            public boolean onCellClicked(Date date) {

                return false;
            }
        });
    }
}
