package com.reciproci.fridaytask.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reciproci.fridaytask.DisplayImage;
import com.reciproci.fridaytask.MainActivity;
import com.reciproci.fridaytask.R;

import java.util.List;

import static com.bumptech.glide.load.engine.DiskCacheStrategy.ALL;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomView> {
    private Context context;
    private List<String> dataList;
    private CallBack mCallBack;

    public RecyclerViewAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomView holder, final int position) {

        Glide.with(context)
                .load(dataList.get(position))
                .apply(new RequestOptions()
                .diskCacheStrategy(ALL)
                .dontAnimate()
                .dontTransform())
                .into(holder.mImageView);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "result="+dataList.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DisplayImage.class);
                intent.putExtra("KEY",dataList.get(position));
                context.startActivity(intent);
//                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context,DisplayImage.class);
//                intent.putExtra("KEY",holder.getLayoutPosition());
//                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class CustomView extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public CustomView(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.rv_imageView);
        }
    }
}
