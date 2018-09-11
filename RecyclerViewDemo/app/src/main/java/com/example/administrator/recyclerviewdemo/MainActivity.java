package com.example.administrator.recyclerviewdemo;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.recyclerviewdemo.adapters.TestAdapter;
import com.example.administrator.recyclerviewdemo.beans.SimpleBean;
import com.heterpu.phbaselib.ui.uicollectionview.UICol_Beans;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private List<SimpleBean> typeArra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, UICol_Beans.recommendedSpanCount));
        configureAdapter();
    }



    private void configureAdapter(){
        initData();
        final TestAdapter adapter = new TestAdapter();
        mRecyclerView.setAdapter(adapter);

        View headerV = new View(this);
        headerV.setBackgroundColor(Color.CYAN);

        View footer = new View(this);
        footer.setBackgroundColor(Color.BLUE);

        ViewGroup.LayoutParams headerParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        ViewGroup.LayoutParams footerParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        headerV.setLayoutParams(headerParam);
        footer.setLayoutParams(footerParam);

//        adapter.setHeaderView(headerV);
//        adapter.setFooterView(footer);


        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int childrenPosition = parent.getChildAdapterPosition(view);
                Rect itemRect = adapter.getItemRectWithPosition(childrenPosition);
                outRect.set(itemRect.left,itemRect.top,itemRect.right,itemRect.bottom);
            }
        });
    }


    private void initData(){
        typeArra = new ArrayList<>();
        for (int i = 0;i < 9;i++){
            SimpleBean bean = new SimpleBean();
            bean.setTitle(""+ i + "title");
            bean.setContent("" + i +"content");
            typeArra.add(bean);
        }
    }

}
