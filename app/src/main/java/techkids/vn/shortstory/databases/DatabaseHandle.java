package techkids.vn.shortstory.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 3/14/2018.
 */

public class DatabaseHandle {
    private static final String TAG = DatabaseHandle.class.toString();   //class kiểm soát function trong database
    private static DatabaseHandle instance; //tạo singleton cho DatabaseHandle

    private MyDatabase myDatabase;
    private SQLiteDatabase storyDatabase;

    private DatabaseHandle(Context context){
        myDatabase = new MyDatabase(context);

    }

    public static DatabaseHandle getDatabaseHandle(Context context){
        if (instance == null){
            return new DatabaseHandle(context);
        }return instance;
    }

    public List<StoryModel> getListStoryModel(){
        storyDatabase = myDatabase.getReadableDatabase();
        List<StoryModel> storyModelList = new ArrayList<>();

        Cursor cursor = storyDatabase.rawQuery("SELECT * FROM tbl_short_story", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){ //truy vấn dữ liệu trong database
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String title = cursor.getString(2);
            String description = cursor.getString(3);
            String content = cursor.getString(4);
            String author = cursor.getString(5);
            boolean bookmark = cursor.getInt(6) != 0;

            StoryModel storyModel = new StoryModel(id, image, title, description, content, author, bookmark);
            storyModelList.add(storyModel);
            cursor.moveToNext();
        }
        cursor.close();
        return storyModelList;
    }
}
