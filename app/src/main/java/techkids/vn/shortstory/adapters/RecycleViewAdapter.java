package techkids.vn.shortstory.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import techkids.vn.shortstory.R;
import techkids.vn.shortstory.databases.StoryModel;
import techkids.vn.shortstory.utils.Util;

/**
 * Created by ADMIN on 3/15/2018.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecyclerViewHolder> {
    private static final String TAG = RecycleViewAdapter.class.toString();
    private List<StoryModel> models;

    public RecycleViewAdapter(List<StoryModel> models){
        this.models = models;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycleview_story, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.iv_item_image.setImageBitmap(Util.decodeBase64(models.get(position).getImage()));
        holder.tv_item_author.setText(models.get(position).getAuthor());
        holder.tv_item_title.setText(models.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
       TextView tv_item_title;
       TextView tv_item_author;
       ImageView iv_item_image;

       private RecyclerViewHolder(View itemView) {
           super(itemView);
           tv_item_title = itemView.findViewById(R.id.tv_item_story_title);
           tv_item_author = itemView.findViewById(R.id.tv_item_story_author);
           iv_item_image = itemView.findViewById(R.id.iv_item_image);
       }
   }
}
