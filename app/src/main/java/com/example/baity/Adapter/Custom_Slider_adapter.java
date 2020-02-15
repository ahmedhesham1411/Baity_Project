package com.example.baity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Custom_slider_model;
import com.example.baity.Model.ImageSlider;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class Custom_Slider_adapter  extends SliderViewAdapter<Custom_Slider_adapter.SliderAdapterVH> {

    private Context context;
    Custom_slider_model custom_slider_model;

    public Custom_Slider_adapter(Context context,Custom_slider_model custom_slider_model) {
        this.context = context;
        this.custom_slider_model= custom_slider_model;
    }

    @Override
    public Custom_Slider_adapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider, null);
        return new Custom_Slider_adapter.SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(Custom_Slider_adapter.SliderAdapterVH viewHolder, final int position) {
        //viewHolder.textViewDescription.setText(custom_slider_model.getName());
        switch (position) {
            case 0:
                String aaa = custom_slider_model.getImage();
                Glide.with(context).load( custom_slider_model.getImage()).into(viewHolder.imageViewBackground);
                //viewHolder.imageViewBackground.setImageResource(Integer.parseInt(custom_slider_model.getImage()));
                break;
            case 1:
                //viewHolder.imageViewBackground.setImageResource(Integer.parseInt(custom_slider_model.getImage()));
                Glide.with(context).load( custom_slider_model.getImage()).into(viewHolder.imageViewBackground);
                break;

            default:
                //v/iewHolder.imageViewBackground.setImageResource(Integer.parseInt(custom_slider_model.getImage()));
                Glide.with(context).load( custom_slider_model.getImage()).into(viewHolder.imageViewBackground);
                break;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        MyTextViewBold textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimgqqq);
            textViewDescription = itemView.findViewById(R.id.slider_nameee);
            this.itemView = itemView;
        }
    }
}
