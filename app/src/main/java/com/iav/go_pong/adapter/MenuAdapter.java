package com.iav.go_pong.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iav.go_pong.R;
import com.iav.go_pong.activity.DataMenuActivity;
import com.iav.go_pong.activity.QuizActivity;
import com.iav.go_pong.helper.Config;
import com.iav.go_pong.model.MenuModel;

import java.util.ArrayList;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MenuModel> menuModels;


    public MenuAdapter(Context context, ArrayList<MenuModel> menuModels) {
        this.context = context;
        this.menuModels = menuModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_menu, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context).load(menuModels.get(position).getImageMenu()).into(holder.ivMenu);
        holder.tvMenu.setText(menuModels.get(position).getNamaMenu());

        holder.cvklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuModels.get(position).getNamaMenu().contains("LATIHAN")) {

                    Intent intent = new Intent(new Intent(context, QuizActivity.class));
                    intent.putExtra(Config.BUNDLE_NAME_MENU, menuModels.get(position).getNamaMenu());
                    context.startActivity(intent);
                } else {

                    Intent intent = new Intent(new Intent(context, DataMenuActivity.class));
                    intent.putExtra(Config.BUNDLE_NAME_MENU, menuModels.get(position).getNamaMenu());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvklik;
        private ImageView ivMenu;
        private TextView tvMenu;

        public ViewHolder(View itemView) {
            super(itemView);

            cvklik = itemView.findViewById(R.id.cvklik);
            ivMenu = itemView.findViewById(R.id.iv_Menu);
            tvMenu = itemView.findViewById(R.id.tv_menu);
        }
    }
}
