package com.ilyzs.basecompat.util;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.ilyzs.basecompat.bean.User;
import com.ilyzs.basecompat.dao.UserDao;

/**
 * Created by zhangshu on 2017/11/21.
 */

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class CompatDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
