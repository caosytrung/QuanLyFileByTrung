package draweralayout.t3h.com.quanlyfilebytrung;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by caotr on 17/08/2016.
 */
public class SearchActivity extends Activity implements TextWatcher {
    private RecyclerView mRecyclerView;
    private AdapterRecyclerView mAdapterRecyclerView;
    private ArrayList<ItemView> mItemViews;
    private EditText mEdtSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        initCommon();
    }

    private void initViews(){
        mEdtSearch = (EditText) findViewById( R.id.edt_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_search);
        mEdtSearch.addTextChangedListener(this);
    }

    private void initCommon(){
        mItemViews = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapterRecyclerView = new AdapterRecyclerView(this,mItemViews);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapterRecyclerView);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    private void listSearch(String str,String path,ArrayList<ItemView> dm){

        File file = new File(path);
        File[] files = file.listFiles();
        for ( int i = 0; i <files.length; i++) {
            String a = files[i].getPath();
            if(a.contains(str)){
                dm.add(new ItemView(a,false,false));
            }
            if ( files[i].isDirectory() ) {
                listSearch(str,files[i].getPath(), dm);
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String str = mEdtSearch.getText().toString();
        String path = Environment.getExternalStorageDirectory().getPath();
        mItemViews.clear();
        listSearch(str,path,mItemViews);
        mAdapterRecyclerView.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
