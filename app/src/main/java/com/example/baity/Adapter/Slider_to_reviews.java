package com.example.baity.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baity.Model.ImageSlider;
import com.example.baity.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class Slider_to_reviews extends SliderViewAdapter<Slider_to_reviews.SliderAdapterVH> {

    Context context;
    ImageSlider imageSlider;

    public Slider_to_reviews(Context context,ImageSlider imageSlider) {
        this.imageSlider= imageSlider;
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_itemm, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        //viewHolder.textViewDescription.setText(imageSlider.getName());
/*        viewHolder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "ddd", Toast.LENGTH_SHORT).show();
            }
        });*/
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
            viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*                    Intent intent = new Intent(context,Review_company.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);*/
                    Toast.makeText(context, "sssddd", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getCount() {
        return 4;
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        FrameLayout frameLayout;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imgwwwwww);
            //frameLayout = itemView.findViewById(R.id.myframe1);
            //textViewDescription = itemView.findViewById(R.id.slider_name);
            this.itemView = itemView;
        }
    }
}
