package techkids.vn.shortstory.features;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import techkids.vn.shortstory.R;
import techkids.vn.shortstory.adapters.RecycleViewAdapter;
import techkids.vn.shortstory.databases.DatabaseHandle;

/**
 * Created by ADMIN on 3/22/2018.
 */

public class RecycleViewMainActivity {
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private Activity activity;

    public RecycleViewMainActivity(Activity activity){
        this.activity = activity;
        recyclerView = activity.findViewById(R.id.rv_main_list_story);
        recycleViewAdapter = new RecycleViewAdapter(DatabaseHandle.getDatabaseHandle(activity).getListStoryModel());

    }

    public void setupLinearLayoutRecyclerView(){
        DividerItemDecoration decoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);         //add a line between items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    public void setupGridLayoutRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
