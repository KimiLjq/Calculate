package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TaxFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private TextView tv_AT_money;
    private TextView tv_total;
    private TextView tv_tax;
    private TextView tv_accu_fund;
    private TextView tv_accu;
    private Spinner sp_tax_class;
    private EditText et_money;
    private Spinner sp_city;
    private ExpandableListView expandableListView;

    private ArrayList<String>  taxs_class;
    private ArrayList<String>  citys;
    private String tax;
    private String city;
//    private int count = 1;
    private Context mContext;

    private BaseAdapter taxAdapter;
    private BaseAdapter cityAdapter;
    private MyExtendableListViewAdapter myExtendableListViewAdapter;


    public String[] groupString = {"五险一金"};
    public String[][] childString ={{"公积金", "医疗", "养老", "失业","工伤","生育"}};


    public TaxFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tax_fragment, container, false);
        mContext = view.getContext();

        bindView(view);
        return view;
    }

    private void bindView(View view){
        tv_AT_money = view.findViewById(R.id.tv_AT_money);
        tv_total = view.findViewById(R.id.tv_total);
        tv_tax = view.findViewById(R.id.tv_tax);
        tv_accu_fund = view.findViewById(R.id.tv_accu_fund);
        sp_tax_class = view.findViewById(R.id.sp_tax_class);
        et_money = view.findViewById(R.id.et_money);
        sp_city = view.findViewById(R.id.sp_city);
//        tv_accu = view.findViewById(R.id.tv_accu);
        expandableListView = view.findViewById(R.id.elv_tax);

        taxs_class = new ArrayList<>();
        citys = new ArrayList<>();

        taxs_class.add("固定月薪");
        taxs_class.add("年终奖");

        citys.add("广州");
        citys.add("北京");
        citys.add("上海");

        taxAdapter = new MyAdapter<String>(taxs_class,R.layout.myspinner_layout) {

            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_item,obj);
            }
        };
        sp_tax_class.setAdapter(taxAdapter);

        cityAdapter = new MyAdapter<String>(citys,R.layout.myspinner_layout) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_item,obj);
            }
        };
        sp_city.setAdapter(cityAdapter);

        sp_tax_class.setOnItemSelectedListener(this);
        sp_city.setOnItemSelectedListener(this);

        et_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //输入文字前触发
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //text改变过程中，一般在此加入监听事件。
                String str = s.toString();
                change(str);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //输入后触发
            }
        });

//        tv_accu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;
//                if(count % 2==0 ){
//
//                }
//            }
//        });


        /*********************************/
        myExtendableListViewAdapter = new MyExtendableListViewAdapter(groupString,childString);
        expandableListView.setAdapter(myExtendableListViewAdapter);
        //设置分组的监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str = et_money.getText().toString();
        switch (parent.getId()){
            case R.id.sp_tax_class:
                tax = parent.getItemAtPosition(position).toString();
                change(str);
                break;
            case R.id.sp_city:
                city = parent.getItemAtPosition(position).toString();
                change(str);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void change(String str){
        double money = 0;
        try{
            money = Double.parseDouble(str);
        }catch (Exception e){
            Log.e("转换出错","TaxFragment.java");
        }

        CountTax countTax = new CountTax(money);
        float tax_money = countTax.countTax();
        float old = countTax.countOld();
        float medicate = countTax.countMedicate();
        float outWork = countTax.countOutWork();
        float house = countTax.countHouse();
        float accuFund = old + medicate + outWork + house;

        childString[0][0] = "公积金";
        childString[0][1] = "医疗";
        childString[0][2] = "养老";
        childString[0][3] = "失业";
        childString[0][4] = "工伤";
        childString[0][5] = "生育";

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if(tax.equals("固定月薪")){
            float[] accu = {house,medicate,old,outWork,0.00f,0.00f};

            tv_AT_money.setText(decimalFormat.format(money-tax_money-accuFund));
            tv_accu_fund.setText(decimalFormat.format(house));
            tv_tax.setText(decimalFormat.format(tax_money));
            tv_total.setText(decimalFormat.format(tax_money+accuFund));

            for(int i=0;i<childString[0].length;i++){
                childString[0][i] = childString[0][i]+":"+decimalFormat.format(accu[i]);
            }
            myExtendableListViewAdapter.notifyDataSetChanged();
        }else{
            float[] accu = {0.00f,0.00f,0.00f,0.00f,0.00f,0.00f};

            tv_AT_money.setText(decimalFormat.format(money-tax_money));
            tv_accu_fund.setText(decimalFormat.format(0.00f));
            tv_total.setText(decimalFormat.format(tax_money));
            tv_tax.setText(decimalFormat.format(tax_money));

            for(int i=0;i<childString[0].length;i++){
                childString[0][i] = childString[0][i]+":"+decimalFormat.format(accu[i]);
            }

            myExtendableListViewAdapter.notifyDataSetChanged();
        }
    }
}
