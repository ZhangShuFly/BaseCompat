package com.ilyzs.basecompat.util;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ilyzs.basecompat.base.CompatBaseApplication;
import com.ilyzs.basecompat.dao.UserDao;

/**
 * Created by zhangshu on 2017/11/24.
 */

public class DBHelper {
    private static DBHelper instance;
    private CompatDatabase db;

    private DBHelper(){}

    private DBHelper(Context context){
           db = Room.databaseBuilder(context,CompatDatabase.class,"component_db.db").build();
    }

    public static   DBHelper getInstance(Context context){
        if(null == instance){
            instance = new DBHelper(context);
        }
        return  instance;
    }

    public CompatDatabase    getDb(){
        return db;
    }

    public void onDestory(){
        db.close();
    }
}
