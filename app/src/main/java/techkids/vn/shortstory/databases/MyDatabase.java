package techkids.vn.shortstory.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by ADMIN on 3/14/2018.
 */

public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "short_story.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
