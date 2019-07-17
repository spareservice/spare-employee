package com.example.spareservice.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Annonce;
import com.example.spareservice.data.model.Mission;
import com.example.spareservice.data.model.Service;
import com.example.spareservice.data.service.NetworkProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissionViewHolder> {

    private List<Mission> weaponList;
    private ItemClickListener itemClickListener;

    public void setWeaponList(List<Mission> weaponList) {
        this.weaponList = weaponList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull @Override public MissionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mission_item, viewGroup, false);
        return new MissionViewHolder(view);
    }

    @Override public void onBindViewHolder (@NonNull MissionViewHolder weaponViewHolder, int i) {
        Mission weapon = weaponList.get(i);
        if(weapon.getIsValide().equals("true")) {
            weaponViewHolder.imageView.setBackgroundResource(R.drawable.ic_check);
        } else {
            weaponViewHolder.imageView.setBackgroundResource(R.drawable.ic_send);
        }
        NetworkProvider.getInstance().getAnnonceById(weapon.getIdAnnonce(), new NetworkProvider.Listener<List<Annonce>>() {
            @Override
            public void onSuccess(List<Annonce> data) {
                weaponViewHolder.desc.setText(data.get(0).getDetailAnnonce() + "\nLe" + data.get(0).getDebutDate() + " à " + data.get(0).getDebutHeure());
                NetworkProvider.getInstance().getService(data.get(0).getIdService(), new NetworkProvider.Listener<List<Service>>() {
                    @Override
                    public void onSuccess(List<Service> data) {
                        weaponViewHolder.title.setText(data.get(0).getNomService());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
            }

            @Override
            public void onError(Throwable t) {

            }
        });

        if (itemClickListener != null) {
            weaponViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    itemClickListener.onClick(weapon);
                }
            });
        }
    }

    @Override public int getItemCount() {
        return weaponList != null ? weaponList.size() : 0;
    }



    class MissionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_mission_title)
        TextView title;
        @BindView(R.id.item_mission_desc)
        TextView desc;
        @BindView(R.id.item_mission_logo)
        ImageView imageView;

        public MissionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(Mission mission);
    }

}
