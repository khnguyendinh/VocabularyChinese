package jsc.com.lampstudio.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import jsc.com.lampstudio.Models.VocabularyObj;
import jsc.com.lampstudio.util.Constant;

import static android.R.attr.id;

/**
 * Created by AdministratorPC on 12/11/2015.
 */
public class GrammarDBHelper extends SQLiteOpenHelper {
    static String DATABASE_PATH = "/data/data/jsc.com.lampstudio.vocabularychinese/databases/";
//    static String DATABASE_NAME = "nguphapdb.sqlite";
    static String DATABASE_NAME = "db_chinese.sqlite";
    static int DATABASE_VERSION = 4;
    private SQLiteDatabase myDataBase;
    private final Context mContext;

    public GrammarDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }
    public void closeDataBase(){
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1>i)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * This method will create database in application package /databases
     * directory when first time application launched
     */
    public void createDataBase() throws IOException {
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException mIOException) {
                mIOException.printStackTrace();
                throw new Error("Error copying database");
            } finally {
                this.close();
            }
        }else {
            Log.d("GrammarDB", "createDataBase else " );
        }
    }

    /**
     * This method checks whether database is exists or not *
     */
    private boolean checkDataBase() {
        try {
            final String mPath = DATABASE_PATH + DATABASE_NAME;
            final File file = new File(mPath);
            if (file.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method will copy database from /assets directory to application
     * package /databases directory
     */
    private void copyDataBase() throws IOException {
        try {

            InputStream mInputStream = mContext.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream mOutputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = mInputStream.read(buffer)) > 0) {
                mOutputStream.write(buffer, 0, length);
            }
            mOutputStream.flush();
            mOutputStream.close();
            mInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, VocabularyObj> selectAllVocabularyObj() {
        HashMap<String, VocabularyObj> result = new HashMap<>();
//        String sql = "SELECT * FROM " + Constant.TBL_VOCABULARY + " ORDER BY level ASC ";
        String sql = "SELECT * FROM " + Constant.TBL_VOCABULARY;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String column1 = cursor.getString(cursor.getColumnIndex(Constant.ENGLISH));
                String column2 = cursor.getString(cursor.getColumnIndex(Constant._IMAGE));

                String column3 = cursor.getString(cursor.getColumnIndex(Constant.PINYIN));
                String column4 = cursor.getString(cursor.getColumnIndex(Constant.HANZI));
                String column5 = cursor.getString(cursor.getColumnIndex(Constant.LISTEN));
                result.put(column1, new VocabularyObj(column1, column2, column3, column4,column5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        myDataBase.close();
        return result;
    }

    public VocabularyObj selectGrammarObjById(String english) {
        VocabularyObj result = null;
        String sql = "SELECT * FROM " + Constant.TBL_VOCABULARY + " WHERE English = " + english;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String column1 = cursor.getString(cursor.getColumnIndex(Constant.ENGLISH));
                String column2 = cursor.getString(cursor.getColumnIndex(Constant._IMAGE));

                String column3 = cursor.getString(cursor.getColumnIndex(Constant.PINYIN));
                String column4 = cursor.getString(cursor.getColumnIndex(Constant.HANZI));
                String column5 = cursor.getString(cursor.getColumnIndex(Constant.LISTEN));
                result =  new VocabularyObj(column1, column2, column3, column4,column5);
            } while (cursor.moveToNext());
        }
        cursor.close();
        myDataBase.close();
        return result;
    }

    /**
     * This method open database for operations *
     */
    public boolean openDataBase() throws SQLException {
        String mPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mPath, null,
                SQLiteDatabase.OPEN_READWRITE);
        return myDataBase.isOpen();
    }

    /**
     * This method close database connection and released occupied memory *
     */
    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }


}
