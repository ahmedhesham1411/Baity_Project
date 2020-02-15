package com.example.baity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.example.baity.Activities.Child.Sub_category;
import com.example.baity.Activities.Child_resault.Sub_category_result;
import com.example.baity.Activities.Slider_Result.Slider_result;
import com.example.baity.Model.Search_image_model;
import com.example.baity.Model.Search_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class Search_adapter  extends ArrayAdapter {

    private List<String> dataList;
    private Context mContext;
    private int itemLayout;
    Preferences preferences;
    private ListFilter listFilter = new ListFilter();
    private List<String> dataListAllItems;
    List<Search_model> search_models;
    List<Integer> intArray;
    List<String> imagesList;

    public Search_adapter(Context context, int resource, List<String> storeDataLst, List<Search_model> search_models, Integer[] idArray) {
        super(context, resource, storeDataLst);
        dataList = storeDataLst;
        mContext = context;
        itemLayout = resource;
        this.search_models=search_models;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public String getItem(int position) {
        Log.d("CustomListAdapter",
                dataList.get(position));
        return dataList.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }


        TextView strName = (TextView) view.findViewById(R.id.text_view_list_item);
        strName.setText(getItem(position));


        List<String> dataList1 = new ArrayList<String>();
        for (int i = 0; i < search_models.size();i++){
            dataList1.add(search_models.get(i).getNameA());
        }

        strName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int indexx = dataList1.indexOf(getItem(position));
                String status = search_models.get(indexx).getFind();
                String name = search_models.get(indexx).getNameA();
                int searchid = search_models.get(indexx).getId();
                List<Search_image_model> image_modelList = search_models.get(indexx).getSearch_image_models();
                imagesList = new ArrayList<String>();
                for (int i=0;i<image_modelList.size();i++){
                    imagesList.add(image_modelList.get(i).getImagepath());
                }

                if (status.equals("child")){
                    preferences.Save_Child_id(mContext,searchid);
                    preferences.Save_Child_name(mContext,name);
                    Intent intent = new Intent(mContext, Sub_category_result.class);
                    mContext.startActivity(intent);
                }

                else if (status.equals("main")){
                    preferences.SaveMainCat_id(mContext,searchid);
                    preferences.SaveMainCat_name(mContext,name);
                    String mainImage = "http://baitykw.com/Content/Images/" + imagesList.get(0);
                    preferences.SaveMainCat_image(mContext,mainImage);
                    Intent intent = new Intent(mContext, Slider_result.class);
                    mContext.startActivity(intent);
                }

                else if (status.equals("Sub")){
                    preferences.Save_Sub_id(mContext,searchid);
                    preferences.Save_Sub_name(mContext,name);
                    Gson gson = new Gson();
                    String json = gson.toJson(imagesList);
                    preferences.Save_listt(mContext,json);
                    Intent intent = new Intent(mContext, Sub_category.class);
                    mContext.startActivity(intent);
                }
            }
        });
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (lock) {
                    dataListAllItems = new ArrayList<String>(dataList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<String> matchValues = new ArrayList<String>();

                for (String dataItem : dataListAllItems) {
                    if (dataItem.toLowerCase().startsWith(searchStrLowerCase)) {
                        matchValues.add(dataItem);
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<String>)results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}