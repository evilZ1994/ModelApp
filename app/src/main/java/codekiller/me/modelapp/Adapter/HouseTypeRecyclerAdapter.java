package codekiller.me.modelapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import codekiller.me.modelapp.Bean.HouseType;
import codekiller.me.modelapp.ImageViewActivity;
import codekiller.me.modelapp.R;

/**
 * Created by Lollipop on 2018/3/23.
 */

public class HouseTypeRecyclerAdapter extends RecyclerView.Adapter<HouseTypeRecyclerAdapter.ViewHolder> {
    private List<HouseType> houseTypes;
    private Context context;
    private LayoutInflater layoutInflater;

    public HouseTypeRecyclerAdapter(List<HouseType> houseTypes, Context context){
        this.houseTypes = houseTypes;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.house_type_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HouseType houseType = houseTypes.get(position);
        holder.title.setText(houseType.getTitle());
        holder.img.setImageURI(houseType.getImgUri());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageViewActivity.class);
                intent.putExtra("image_uri", houseType.getImgUri().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return houseTypes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private SimpleDraweeView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.house_type_item_title);
            img = itemView.findViewById(R.id.house_type_item_img);
        }
    }
}
