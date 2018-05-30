package com.example.wxj.my_firstapp.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.wxj.my_firstapp.bean.Video;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static final String DATABASE_NAME = "db_data";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "tb_video";
    private static final String ID = "_id";
    private static final String VIDEO_ID = "video_id";
    private static final String VIDEO_NAME = "video_name";
    private static final String VIDEO_URL = "video_url";
    private static final String VIDEO_THUMB_URL = "video_thumb_url";
    private static final String TAG = "DBUtil";

    private SQLiteDatabase db = null;

    private static DBUtil mDBUtil = null;

    private class DBOpenHelper extends SQLiteOpenHelper {
        private static final String CREATE_TABLE = "create table " + TABLE_NAME
                + "(" + ID + " integer primary key autoincrement,"
                + VIDEO_ID + " integer not null,"
                + VIDEO_NAME + " text not null,"
                + VIDEO_URL + " text not null,"
                + VIDEO_THUMB_URL + " text not null);";

        public DBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            onCreate(db);
        }
    }

    private DBUtil(Context context) {
        DBOpenHelper helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBUtil getInstance(Context context) {
        if (mDBUtil == null) {
            synchronized (DBUtil.class) {
                mDBUtil = new DBUtil(context);
            }
        }
        return mDBUtil;
    }

    public void insert(Video video) {

        ContentValues cv = new ContentValues();
        cv.put(VIDEO_ID, video.getId());
        cv.put(VIDEO_NAME, video.getName());
        cv.put(VIDEO_URL, video.getVideoUrl());
        cv.put(VIDEO_THUMB_URL, video.getVideoImageUrl());
        // 插入ContentValues中的数据
        db.insert(TABLE_NAME, null, cv);
    }

    public void update(String videoName, String videoUrl) {
        ContentValues cv = new ContentValues();
        cv.put(VIDEO_URL, videoUrl);
        db.update(TABLE_NAME, cv, VIDEO_NAME + "= ?", new String[]{videoName});
    }

    public void delete(String videoName) {
        db.delete(TABLE_NAME, VIDEO_NAME + " = ?", new String[]{videoName});
    }

    public List<Video> query() {
        List<Video> videoList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            int videoId = c.getInt(c.getColumnIndex(VIDEO_ID));
            String videoName = c.getString(c.getColumnIndex(VIDEO_NAME));
            String videoUrl = c.getString(c.getColumnIndex(VIDEO_URL));
            String videoThumbUrl = c.getString(c.getColumnIndex(VIDEO_THUMB_URL));

            videoList.add(new Video(videoId, videoName, videoUrl, videoThumbUrl));

        }
        c.close();

        return videoList;
    }

    public void close() {
        db.close();
    }
}
