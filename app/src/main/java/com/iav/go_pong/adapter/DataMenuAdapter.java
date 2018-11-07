package com.iav.go_pong.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.go_pong.R;
import com.iav.go_pong.activity.DetailActivity;
import com.iav.go_pong.helper.Config;
import com.iav.go_pong.model.DataMenuModel;

import java.util.ArrayList;


public class DataMenuAdapter extends RecyclerView.Adapter<DataMenuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DataMenuModel> dataMenuModels;


    public DataMenuAdapter(Context context, ArrayList<DataMenuModel> dataMenuModels) {
        this.context = context;
        this.dataMenuModels = dataMenuModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_data, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context).load(dataMenuModels.get(position).getImageUrl()).into(holder.ivMenu);
        holder.tvMenu.setText(dataMenuModels.get(position).getJudul());
        holder.tv_menu_nama_teknik.setText(dataMenuModels.get(position).getNamaTeknik());

        holder.cvklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(context, DetailActivity.class));
                intent.putExtra(Config.NAMA_TEKNIK_JUDUL, dataMenuModels.get(position).getNamaTeknik());
                intent.putExtra(Config.IMAGE_URL, dataMenuModels.get(position).getImageUrl());
                intent.putExtra(Config.VIDEO_YOUTUBE_ID, dataMenuModels.get(position).getVideoYoutubeId());
                intent.putExtra(Config.DESKRIPSI, dataMenuModels.get(position).getDeskripsi());
                intent.putExtra(Config.TYPE, dataMenuModels.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMenuModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cvklik;
        private ImageView ivMenu;
        private TextView tvMenu;
        private TextView tv_menu_nama_teknik;
        private Button btnLihatVideo;
        public ViewHolder(View itemView) {
            super(itemView);

            cvklik = itemView.findViewById(R.id.cvklik);
            ivMenu = itemView.findViewById(R.id.iv_Menu);
            tvMenu = itemView.findViewById(R.id.tv_menu_judul);
            tv_menu_nama_teknik = itemView.findViewById(R.id.tv_menu_nama_teknik);
            btnLihatVideo = itemView.findViewById(R.id.btn_lihat_video);
        }
    }
}
