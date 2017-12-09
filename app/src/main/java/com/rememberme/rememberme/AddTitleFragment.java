package com.rememberme.rememberme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.rememberme.rememberme.R.id.BtnEndDate;
import static com.rememberme.rememberme.R.id.Btnstartdate;

/**
 * Created by samsung on 2017-12-06.
 */

public class AddTitleFragment extends Fragment {


    @Nullable
    @Override


// 여기서 버튼을 누르면 달력이 나오고, 달력을 설정해서 데이 숫자가 넘어와야한다.
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle data) {
        final ViewGroup addTitle = (ViewGroup) inflater.inflate(R.layout.fragment_add_title,container,false);
//Btnstartdate Btnenddate
        String startDay=getArguments().getString("startDay");
        String endDay=getArguments().getString("endDay");
        Button startdate = (Button) addTitle.findViewById(Btnstartdate);
        Button enddate = (Button) addTitle.findViewById(BtnEndDate);

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup)(addTitle.getParent())).removeView(addTitle);
                Intent goTocalendar = new Intent(getActivity(), trip_calendar_Activity.class);
                getActivity().startActivity(goTocalendar);
            }
        });
        startdate.setText(startDay);
        enddate.setText(endDay);

        return addTitle;
    }
}
