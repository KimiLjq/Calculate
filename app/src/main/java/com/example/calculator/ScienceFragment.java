package com.example.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ScienceFragment extends Fragment implements View.OnClickListener{
    private ListView lv_record;
    private LVAdapter<String> lvAdapter;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button_point;
    private Button button_add;
    private Button button_sub;
    private Button button_mulp;
    private Button button_divide;
    private Button button_left_bracket;
    private Button button_right_bracket;
    private Button button_back;
    private Button button_clean;
    private Button button_equal;


    private final ArrayList<String> formulaList = new ArrayList();
    private boolean isCount = true;

    public ScienceFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.science_fragment, container, false);

        initView(view);

        lvAdapter = new LVAdapter<String>(formulaList,R.layout.count_record) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_record,obj);
            }
        };
        lv_record.setAdapter(lvAdapter);

        return view;
    }

    private void initView(View view){
        lv_record = view.findViewById(R.id.lv_record);

        button0 = view.findViewById(R.id.bt_zeno);
        button1 = view.findViewById(R.id.bt_one);
        button2 = view.findViewById(R.id.bt_two);
        button3 = view.findViewById(R.id.bt_three);
        button4 = view.findViewById(R.id.bt_four);
        button5 = view.findViewById(R.id.bt_five);
        button6 = view.findViewById(R.id.bt_six);
        button7 = view.findViewById(R.id.bt_seven);
        button8 = view.findViewById(R.id.bt_eight);
        button9 = view.findViewById(R.id.bt_nine);
        button_point = view.findViewById(R.id.bt_point);
        button_add = view.findViewById(R.id.bt_add);
        button_sub = view.findViewById(R.id.bt_sub);
        button_mulp = view.findViewById(R.id.bt_multiply);
        button_divide = view.findViewById(R.id.bt_divide);
        button_equal = view.findViewById(R.id.bt_equal);
        button_left_bracket = view.findViewById(R.id.bt_left_bracket);
        button_right_bracket = view.findViewById(R.id.bt_right_bracket);
        button_back = view.findViewById(R.id.bt_back);
        button_clean =view.findViewById(R.id.bt_clean);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_mulp.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_right_bracket.setOnClickListener(this);
        button_left_bracket.setOnClickListener(this);
        button_back.setOnClickListener(this);
        button_clean.setOnClickListener(this);
        button_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String count_formula;
        try{
            if(isCount){
                count_formula = null;
                isCount = false;
                if(!formulaList.get(formulaList.size()-1).equals(" ")){
                    formulaList.add(" ");
                }
            }else count_formula = formulaList.get(formulaList.size()-1);
        }catch (Exception e){
            count_formula = null;
            formulaList.add(" ");
        }

        StringBuffer input;

        try{
            if(count_formula.equals(" ")){
                input =  new StringBuffer();
            }else input = new StringBuffer(count_formula);
        }catch (Exception e){
            input =  new StringBuffer();
        }



        switch (id){
            case R.id.bt_zeno:
                input.append("0");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_one:
                input.append("1");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_two:
                input.append("2");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_three:
                input.append("3");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_four:
                input.append("4");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_five:
                input.append("5");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_six:
                input.append("6");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_seven:
                input.append("7");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_eight:
                input.append("8");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_nine:
                input.append("9");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_point:
                input.append(".");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_add:
                input.append("+");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_sub:
                input.append("-");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_multiply:
                input.append("*");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_divide:
                input.append("/");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_left_bracket:
                input.append("(");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_right_bracket:
                input.append(")");
                count_formula = input.toString();
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_back:
                try{
                    input.deleteCharAt(input.length()-1);
                    count_formula = input.toString();
                }catch (Exception e){
                    count_formula="";
                }

                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_clean:
                try{
                    input.delete(0,input.length());
                    count_formula = input.toString();
                }catch (Exception e){
                    count_formula="";
                }
                formulaList.set(formulaList.size()-1,count_formula);
                lvAdapter.notifyDataSetChanged();
                break;
            case  R.id.bt_equal:
                if(count_formula==null||count_formula.equals(" ")||count_formula.equals("")){
                    isCount = true;
                    break;
                }
                double result = Calculator.conversion(count_formula);
                count_formula = Double.toString(result);
                input = new StringBuffer(count_formula);
                input.insert(0,"= ");
                count_formula = input.toString();
                formulaList.add(count_formula);
                lvAdapter.notifyDataSetChanged();
                isCount = true;
                break;
        }
    }


}
