package com.example.peter.myapplication;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.ArrayList;

/**
 * Created by Peter on 28/11/2016.
 * *  Manual:
 *     First:  Initialize BaseGridView
 *             eg.{
 *              setContentView(R.layout.activity_main);
 *              gridV = (BaseGridView) findViewById(R.id.mGridView);
 *              listV.setSource(this,this);
 *             }
 *     Second: Inplements DataSource interface.
 *              eg.{
 *                implements BaseGridView.DataSource.
 *                override several method in dataSouce.
 *                Use ViewLoader to custom your view and get higher proficiency.
 *              }
 *    Third: when your data are prepared already.call reloadData to refresh UI in ListView.
 *
 *    Good luck , enjoy yourself !!!
 */


public class BaseGridView extends GridView {

    /**
     * DataSource use to fetch data.
     */
    private DataSource source;

    /**
     * dataArray use to store cell state.
     */
    private  ArrayList<Indexpath> dataArray = new ArrayList<>();

    /**
     * ArrayAdapter use to customize view.
     */
    private  CustomArrayAdapter adapter;


    /**
     * Constructor
     * @param context  context
     * @param attrs attrs
     */
    BaseGridView (Context context, AttributeSet attrs){
        super(context,attrs);
    }


    /**
     * Set datasource from Outside.
     * @param context context from outside.
     * @param source get delegate from outside.
     */
    public void setSource(Context context,DataSource source) {
        this.source = source;
        CustomArrayAdapter arrayAdapter = new CustomArrayAdapter(context,0,dataArray);
        setAdapter(arrayAdapter);
        this.adapter = arrayAdapter;
    }


    /**
     * Call this method to refresh data.
     */
    public void reloadData(){initDataSource();}


    /**
     * Datasource interface.
     */
    public interface DataSource {

        /**
         * Fetch number of section.
         * @return section number.
         */
        int numberOfSection();

        /**
         * Fetch row number from a concrete section.
         * @param section  section number starts from 0.
         * @return
         */
        int numberOfRowInsection(int section);

        /**
         * Fetch cell's ViewCallBack From Outside.
         * @param section  Section number starts from 0.
         * @param row Row number starts from 0.
         * @param holder  Object Holder for reuse.
         * @return  A ViewCallBack for cell.
         */
        ViewCallBack cellForRow(int section, int row ,Object holder);
    }


    /**
     * Indexpath use to store position of each cell.
     */
    private class Indexpath {
        int section;
        int row;
        Indexpath(int section,int row){
            this.section = section;
            this.row = row;
        }
        boolean isEqualType(Indexpath index) {
            return  (this.section == index.section);
        }
    }


    /**
     * ViewCallback
     * @param <T>
     */
    public static class ViewCallBack<T> {
        Indexpath indexpath;
        View view;
        T holder;
        ViewCallBack(T holder,View view){
            this.holder = holder;
            this.view = view;
        }
    }


    /**
     * Initializing dataSoure after call reloadData().
     */
    private void initDataSource(){
        dataArray.clear();
        int section = source.numberOfSection();
        for (int i = 0 ; i < section; i++) {
            int row = source.numberOfRowInsection(i);
            for(int j = 0;j < row;j++) {
                Indexpath index = new Indexpath(i,j);
                dataArray.add(index);
                }
            }
          adapter.notifyDataSetChanged();
    }


    /**
     * Customize array adapter.
     */
    private class CustomArrayAdapter extends ArrayAdapter<Indexpath> {

        CustomArrayAdapter(Context context,int resource,ArrayList<Indexpath> list){
            super(context,resource,list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Indexpath path = getItem(position);
            if (convertView == null){
                return getViewFrom(path);
            }
            else
            {
                ViewCallBack callBack = (ViewCallBack) convertView.getTag(R.id.TAG_ID_COL);
                if (!callBack.indexpath.isEqualType(path)){
                   return getViewFrom(path);
                }else {
                    source.cellForRow(path.section,path.row,callBack.holder);
                }
                return convertView;
            }
        }
        private View getViewFrom(Indexpath path) {
            ViewCallBack bv;
            bv = source.cellForRow(path.section,path.row,null);
            bv.indexpath = path;
            View convertView = bv.view;
            convertView.setTag(R.id.TAG_ID_COL,bv);
            return convertView;
        }
    }
}
