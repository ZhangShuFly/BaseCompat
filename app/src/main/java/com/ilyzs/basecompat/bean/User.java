package com.ilyzs.basecompat.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by zhangshu on 2017/11/19.
 */

@Entity
public class User {

    @PrimaryKey
    public  int id;

    public  String name;

    public  Integer age;
}
