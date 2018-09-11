package com.example.administrator.recyclerviewdemo.adapters;

import android.graphics.Color;
import android.graphics.Rect;
import android.hardware.SensorEventCallback;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.recyclerviewdemo.R;
import com.example.administrator.recyclerviewdemo.beans.TestSectionModel;
import com.heterpu.phbaselib.ui.uicollectionview.UICol_Adapter;
import com.heterpu.phbaselib.ui.uicollectionview.UICol_Beans;
import com.heterpu.phbaselib.ui.uicollectionview.UICol_Section_Interface;


public class TestAdapter extends UICol_Adapter<TestSectionModel,BaseViewHolder> {


    @Override
    protected int numberOfSectionInView() {
        return 4;
    }

    @Override
    protected int numberOfItemInSection(UICol_Section_Interface.SectionType sectionType, int section) {
        return 6 + section * 2;
    }


    @Override
    protected Rect sectionInsetForSection(int section) {
        if(section == 0) {
            return new Rect(10, 10, 10, 10);
        }else {
            return new Rect(20, 20, 20, 20);
        }
    }


    @Override
    protected int minimiumLineSpacingForSection(int section) {
        return 20;
    }

    @Override
    protected int minimumInteritemSpacingForSection(int section) {
        return 20;
    }


    /**
     * @param sectionType
     * @param section
     * @return
     */
    @Override
    protected TestSectionModel modelForHeaderFooterInSection(UICol_Section_Interface.SectionType sectionType, int section) {
//        if (sectionType == UICol_Section_Interface.SectionType.FOOTER) {

            TestSectionModel model = new TestSectionModel(2, R.layout.header_layout, UICol_Beans.recommendedSpanCount);
            return model;
//        }else{
//            return null;
//        }
    }


    /**
      * @param indexPath
     * @return
     */
    @Override
    protected TestSectionModel modelForItemIndexPath(UICol_Beans.IndexPath indexPath) {
        int count = indexPath.getSection() + 1;
        TestSectionModel model = new TestSectionModel(1, R.layout.item_layout, UICol_Beans.recommendedSpanCount / count);
        return model;
    }


    @Override
    protected void convertHeaderFooterAtSection(UICol_Section_Interface.SectionType sectionType, int section, BaseViewHolder helper, TestSectionModel data) {
        if (sectionType == UICol_Section_Interface.SectionType.FOOTER) {
            helper.setText(R.id.headerText, "I AM FOOTER OF SECTION " + section).setTextColor(R.id.headerText, Color.BLACK);
        }else {
            helper.setText(R.id.headerText, "I AM HEADER OF SECTION " + section).setTextColor(R.id.headerText, Color.RED);
        }
    }


    @Override
    protected void convertItemAtIndexPath(UICol_Section_Interface.SectionType sectionType, UICol_Beans.IndexPath indexpath, BaseViewHolder helper, TestSectionModel data) {
        helper.setText(R.id.itemText, "I AM ITEM OF SECTION " + indexpath.getSection() + " ITEMINDEX" + indexpath.getItemIndex());
    }
}
