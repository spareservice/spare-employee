package com.example.spareservice.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;
import com.example.spareservice.data.model.Annonce;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.WeaponViewHolder>{

    private List<Annonce> weaponList;
    private ItemClickListener itemClickListener;

    public void setWeaponList(List<Annonce> weaponList) {
        this.weaponList = weaponList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull @Override public WeaponViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.annonce_item, viewGroup, false);
        return new WeaponViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull WeaponViewHolder weaponViewHolder, int i) {
        Annonce weapon = weaponList.get(i);
        weaponViewHolder.title.setText(weapon.getDetailAnnonce());
        weaponViewHolder.desc.setText(weapon.getDescriptionAnnonce());

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



    class WeaponViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_annonce_title)
        TextView title;
        @BindView(R.id.item_annonce_desc)
        TextView desc;

        public WeaponViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(Annonce weapon);
    }
}
