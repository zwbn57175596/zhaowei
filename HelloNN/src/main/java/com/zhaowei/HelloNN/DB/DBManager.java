package com.zhaowei.HelloNN.DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.zhaowei.HelloNN.Constant;
import com.zhaowei.HelloNN.DB.pojo.Fruit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager {

    private SQLiteDatabase db = null;
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "HelloNN.db"; // 保存的数据库文件名
    public static final String PACKAGE_NAME = "com.zhaowei.HelloNN";
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME; // 在手机里存放数据库的位置(/data/data/com.cssystem.activity/cssystem.db)

    /**
     * 结果果封装
     * 
     * @param cursor
     * @return
     * @throws UnsupportedEncodingException
     */
    private List<Fruit> cursor2OjbList(Cursor c) throws UnsupportedEncodingException {
        ArrayList<Fruit> result = new ArrayList<Fruit>();
        while (c.moveToNext()) {
            String name = new String(c.getBlob(c.getColumnIndex("name")));
            byte[] desc_data = c.getBlob(c.getColumnIndex("desc"));
            String desc = new String(desc_data == null ? "".getBytes() : desc_data);
            String pinyin = new String(c.getBlob(c.getColumnIndex("pinyin")));
//            String pic = new String(c.getBlob(c.getColumnIndex("pic")));
            result.add(new Fruit(c.getInt(c.getColumnIndex("id")), name, desc, pinyin));
        }
        return result;
    }

    /**
     * 搜索水果
     * 
     * @param keyword
     * @return
     */
    public List<Fruit> searchFruit(String keyword) {
        Cursor c = null;

        List<Fruit> result = new ArrayList<Fruit>();
        try {
            // keyword = new String(keyword.getBytes("GBK"));
            c = db.rawQuery("select * from fruit where name like ? ", new String[] { "%" + keyword + "%" });
            result.addAll(cursor2OjbList(c));
            c.close();
            c = db.rawQuery("select * from fruit where pinyin like ? ", new String[] { "%" + keyword + "%" });
            result.addAll(cursor2OjbList(c));
            return result;
        } catch (UnsupportedEncodingException e) {
            Log.e("dbManage query error", "dbManage query error", e);
        } finally {
            c.close();
        }
        return null;
    }

    /**
     * update person's age
     * 
     * @param person
     */
    public void updateAge(Fruit fruit) {
        ContentValues cv = new ContentValues();
        cv.put("desc", fruit.getDesc());
        db.update("fruit", cv, "name = ?", new String[] { fruit.getName() });
    }

    /**
     * delete old person
     * 
     * @param person
     */
    public void deleteOldPerson(Fruit fruit) {
        // db.delete("fruit", "age >= ?", new
        // String[]{String.valueOf(person.age)});
    }

    /**
     * query all persons, return list
     * 
     * @return List<Person>
     */
    // public List<Fruit> query() {
    // ArrayList<Fruit> result = new ArrayList<Fruit>();
    // Cursor c = queryTheCursor();
    // try {
    // while (c.moveToNext()) {
    // String name = new String(c.getBlob(c.getColumnIndex("name")), "GBK");
    // String desc = new String(c.getBlob(c.getColumnIndex("desc")), "GBK");
    // String pinyin = new String(c.getBlob(c.getColumnIndex("pinyin")), "GBK");
    // result.add(new Fruit(c.getInt(c.getColumnIndex("id")), name, desc,
    // pinyin));
    // }
    //
    // } catch (UnsupportedEncodingException e) {
    // Log.e("dbManage query error", "dbManage query error", e);
    // }
    // c.close();
    // return result;
    // }

    public List<Fruit> query() {
        ArrayList<Fruit> result = new ArrayList<Fruit>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            String name = new String(c.getBlob(c.getColumnIndex("name")));
            byte[] desc_data = c.getBlob(c.getColumnIndex("desc"));
            String desc = new String(desc_data == null ? "".getBytes() : desc_data);
            String pinyin = new String(c.getBlob(c.getColumnIndex("pinyin")));
            // String pic = new String(c.getBlob(c.getColumnIndex("pic")));
            result.add(new Fruit(c.getInt(c.getColumnIndex("id")), name, desc, pinyin));
        }
        c.close();
        return result;
    }

    /**
     * query all persons, return cursor
     * 
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM fruit", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }

    public void add(List<Fruit> fruits) {
        db.beginTransaction();
        try {
            for (Fruit f : fruits) {
                db.execSQL("INSERT INTO fruit(name, pinyin, desc) VALUES (?, ?, ?)",
                        new Object[] { f.getName(), f.getPinyin(), f.getDesc() });
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public DBManager(Context context) {
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        String dbfile = DB_PATH + "/" + DB_NAME;
        Log.d(Constant.LOG_TAG, "initial DB file: " + dbfile);
        try {
            if (!(new File(dbfile).exists())) {
                // 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = context.getAssets().open(DB_NAME); // 欲导入的数据库
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }

            this.db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            Log.d(Constant.LOG_TAG, "success");
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
    }
}
