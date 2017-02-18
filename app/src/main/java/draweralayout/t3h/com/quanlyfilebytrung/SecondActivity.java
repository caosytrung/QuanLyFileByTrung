package draweralayout.t3h.com.quanlyfilebytrung;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by caotr on 16/08/2016.
 */
public class SecondActivity extends Activity implements IDeleteFile, View.OnClickListener {
    private AdapterRecyclerView mAdapter;
    private RecyclerView mRvListItem;
    private ArrayList<ItemView> mItemViews;
    private TextView tvXoa;

    private String mPathRoot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_file);
        initViews();
        initCommons();
        setUpRe();

    }
    private void initViews(){
        mRvListItem = (RecyclerView) findViewById(R.id.rv_list);
        tvXoa = (TextView) findViewById(R.id.tv_xoa);
        tvXoa.setOnClickListener(this);

    }

    private void setUpRe(){
           mItemViews = (ArrayList<ItemView>) getIntent().getSerializableExtra("list");
        mAdapter = new  AdapterRecyclerView(this, mItemViews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvListItem.setLayoutManager(layoutManager);
        mAdapter.setIDeleteFile(this);
        mRvListItem.setAdapter(mAdapter);
    }

    private void initCommons(){
        mItemViews = new ArrayList<>();

    }

    @Override
    public void delete(ArrayList<String> list) {

    }

    @Override
    public void show(boolean isShow) {
        if(isShow){
            tvXoa.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        mAdapter.deleFiles();
        tvXoa.setVisibility(View.GONE);

    }

}
