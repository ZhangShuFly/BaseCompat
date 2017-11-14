package com.ilyzs.libnetwork.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;

import com.ilyzs.libnetwork.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * URL数据读取类
 * Created by zs .
 */

public class URLDataManager {
    public static URLData findURL(Context context, String key){
        XmlResourceParser xrp = context.getApplicationContext().getResources().getXml(R.xml.url);
        URLData urlData = new URLData();
        try {
            int eventType = xrp.getEventType();
            while (eventType!= XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if("node".equals(xrp.getName()) && key.equals(xrp.getAttributeValue(null,"key"))){
                            urlData.setExpires(Long.parseLong(xrp.getAttributeValue(null,"expires")));
                            urlData.setKey(xrp.getAttributeValue(null,"key"));
                            urlData.setNetType(xrp.getAttributeValue(null,"netType"));
                            urlData.setUrl(xrp.getAttributeValue(null,"url"));
                            return  urlData;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventType = xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            xrp.close();
        }
        return  null;
    }
}
