package com.example.calculator;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DrawerLayout drawer_layout;
    private ListView list_left_drawer;
    private Button line_out;
    private TextView tv_top;

    private ArrayList<String> menuLists;
    private ArrayAdapter<String> adapter = null;

    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //隐藏默认标题
        actionBar.setDisplayShowTitleEnabled(false);

        list_left_drawer.setAdapter(adapter);
        setListener();

    }

    private void init(){
        drawer_layout =  findViewById(R.id.drawer_layout);
        list_left_drawer = findViewById(R.id.list_left_drawer);
        line_out = findViewById(R.id.line_out);
        tv_top=findViewById(R.id.tv_top);

        ScienceFragment science = new ScienceFragment();
        fm.beginTransaction().replace(R.id.ly_content,science).commit();

        menuLists = new ArrayList<>();
        menuLists.add("科学计算器");
        menuLists.add("个税计算器");
        adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_expandable_list_item_1,menuLists);

    }

    private void setListener(){
        list_left_drawer.setOnItemClickListener(this);

        line_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String choice = menuLists.get(position);
        if(choice.equals("个税计算器")){
            tv_top.setText("个税计算器");
            TaxFragment taxFragment = new TaxFragment();
            fm.beginTransaction().replace(R.id.ly_content,taxFragment).commit();
        }
        else{
            tv_top.setText("科学计算器");
            ScienceFragment scienceFragment = new ScienceFragment();
            fm.beginTransaction().replace(R.id.ly_content,scienceFragment).commit();
        }

        drawer_layout.closeDrawer(list_left_drawer);
    }

}
