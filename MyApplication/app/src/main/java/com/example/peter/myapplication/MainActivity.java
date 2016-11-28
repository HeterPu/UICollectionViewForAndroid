package com.example.peter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements BaseGridView.DataSource  {
    BaseGridView gridV;
    private int row = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridV = (BaseGridView) findViewById(R.id.mgridview);
        gridV.setSource(this,this);
        gridV.reloadData();
        gridV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = "you click item" + position;
                Toast  toast = Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                row = 10;
                gridV.reloadData();
            }
        });
    }

    @Override
    public int numberOfSection() {
        return 3;
    }

    @Override
    public int numberOfRowInsection(int section) {
        if (section == 0) {
          return  5;
        }
        else
        {
            return 5;
        }
    }




    @Override
    public BaseGridView.ViewCallBack cellForRow(int section, int row, Object holder) {
        if (holder == null) {
            BaseViewHolder.CellStyle cell = new BaseViewHolder.CellStyle();
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

            View  ConvertView;
            switch (section){
                case 0:
                    int ScreenWidth = (int)(getResources().getDisplayMetrics().widthPixels);
                    ConvertView  =  inflater.inflate(R.layout.header_style, null);
                    cell.textV = (TextView)ConvertView.findViewById(R.id.headerT);
                    AbsListView.LayoutParams param;
                    if ((row % 2) != 0)
                    {
                        param  = new AbsListView.LayoutParams(300,300);
                    }
                    else
                    {
                        param  = new AbsListView.LayoutParams(300,300);
                    }
                    ConvertView.setLayoutParams(param);
                    break;
                case 1:
                    ConvertView =  inflater.inflate(R.layout.cell_style, null);
                    cell.textV = (TextView)ConvertView.findViewById(R.id.cellT);
                    break;
                default:
                    ConvertView = inflater.inflate(R.layout.footer_style, null);
                    cell.textV = (TextView)ConvertView.findViewById(R.id.footerT);
                    break;
            }
            String aa = "This is row" + row;
            cell.textV.setText(aa);
            return new BaseGridView.ViewCallBack<>(cell,ConvertView);
        }
        else
        {
            BaseViewHolder.CellStyle cell = (BaseViewHolder.CellStyle)holder;
            String aa = "This is row" + row +"From Reuse";
            cell.textV.setText(aa);
            return null;
        }
    }
}
