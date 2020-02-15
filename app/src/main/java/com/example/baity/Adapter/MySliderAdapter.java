package com.example.baity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baity.Model.ImageSlider;
import com.example.baity.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class MySliderAdapter extends SliderViewAdapter<MySliderAdapter.SliderAdapterVH> {

    private Context context;
    ImageSlider imageSlider;

    public MySliderAdapter(Context context,ImageSlider imageSlider) {
        this.imageSlider= imageSlider;
    }

    @Override
    public MySliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider, null);
        return new MySliderAdapter.SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(MySliderAdapter.SliderAdapterVH viewHolder, final int position) {
        //viewHolder.textViewDescription.setText(imageSlider.getName());
        switch (position) {
            case 0:
                viewHolder.imageViewBackground.setImageResource(imageSlider.getImage());
                break;
            case 1:
                viewHolder.imageViewBackground.setImageResource(imageSlider.getImage());
                break;

            default:
                viewHolder.imageViewBackground.setImageResource(imageSlider.getImage());
                break;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimg);
            //textViewDescription = itemView.findViewById(R.id.slider_name);
            this.itemView = itemView;
        }
    }
}
