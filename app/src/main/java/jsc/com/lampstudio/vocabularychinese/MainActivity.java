package jsc.com.lampstudio.vocabularychinese;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jsc.com.lampstudio.Models.VocabularyObj;
import jsc.com.lampstudio.adapter.VocabularyAdapter;
import jsc.com.lampstudio.db.GrammarDBHelper;

public class MainActivity extends AppCompatActivity {
    private VocabularyAdapter mAdapter;
    private ArrayList<VocabularyObj> vocabularyObjs;
    private HashMap<String, VocabularyObj> mMap;
    private GrammarDBHelper mGHelper;
    private RecyclerView mListGrammarObjTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGHelper = new GrammarDBHelper(MainActivity.this);
        try {
            mGHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mGHelper.openDataBase();
        } catch (SQLException sqlE) {
            throw sqlE;
        }
        mMap = new HashMap<>();
        mMap = mGHelper.selectAllVocabularyObj();
        vocabularyObjs = new ArrayList<>();
//        for (int key : mMap.keySet()) {
//            Log.d("khoand ","key "+key);
//            grammarObjs.add(mMap.get(key));
//        }
        for (int i = 1; i < mMap.size() + 1; i++) {
            vocabularyObjs.add(mMap.get(i));
        }
        mAdapter = new VocabularyAdapter(vocabularyObjs, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,1);
        mListGrammarObjTitle.setLayoutManager(layoutManager);
        mListGrammarObjTitle.setAdapter(mAdapter);
    }
}
