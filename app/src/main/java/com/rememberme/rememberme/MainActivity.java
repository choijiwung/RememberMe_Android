package com.rememberme.rememberme;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CurrentFragment currentFragment;
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currentFragment = new CurrentFragment();
        listFragment = new ListFragment();


        getSupportFragmentManager().beginTransaction().add(R.id.main_container, currentFragment).commit();

        // 이제 탭버튼을 추가

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("현재여행"));
        tabs.addTab(tabs.newTab().setText("여행목록"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {  // 탭버튼을 하나 선택했을 때 자동으로 선택되는 콜벡메소드
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position==0){
                    selected = currentFragment;
                }else if (position == 1) {
                    selected = listFragment;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,selected).commit(); // 이미 선택되었기 때문에, replace를 사용하며, 뜻은 container 안에 선택된 selected를 넣어주라
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}


