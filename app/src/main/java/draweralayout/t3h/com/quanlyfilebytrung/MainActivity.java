package draweralayout.t3h.com.quanlyfilebytrung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TableRow mBtnBoNho;
    private TableRow mBtnNhatKy;
    private TableRow mBtnTaiLieu;
    private TableRow mBtnHinhAnh;
    private TableRow mBtnAmThanh;
    private TableRow mBtnVideo;
    private TextView tvXoa;



    private ArrayList<ItemView> itemViews = new ArrayList<>();
    private RecyclerView mRvHistory;
    private String rootPath;
    private AdapterRVHistory adapterRVHistory = AdapterRVHistory.getInstane();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initCommons();
    }
    private void initViews(){
        mBtnBoNho = (TableRow) findViewById(R.id.btn_bo_nho);
        mBtnBoNho.setOnClickListener(this);
        mRvHistory = (RecyclerView) findViewById(R.id.rv_history);
        mBtnNhatKy = (TableRow) findViewById(R.id.btn_nhat_ky);
        mBtnNhatKy.setOnClickListener(this);
        findViewById(R.id.btn_tai_lieu).setOnClickListener(this);
        findViewById(R.id.btn_hinh_anh).setOnClickListener(this);
        findViewById(R.id.btn_am_thanh).setOnClickListener(this);
        findViewById(R.id.btn_video).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);
        findViewById(R.id.btn_su_dung_bo_nho).setOnClickListener(this);
    }
    private void initCommons(){
        mItemViews = new ArrayList<>();
        rootPath = Environment.getExternalStorageDirectory().getPath();
        adapterRVHistory.setmContext(this);
        adapterRVHistory.setmItemViews(itemViews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvHistory.setLayoutManager(layoutManager);
        mRvHistory.setAdapter(adapterRVHistory);


    }

    private ArrayList<ItemView> mItemViews;


    @Override
    public void onClick(View v) {

        ArrayList<ItemView> itemViews;
        switch (v.getId()){
            case R.id.btn_su_dung_bo_nho:
                startActivity(new Intent(this,BoNhoActivity.class));

                break;
            case R.id.tv_search:
                Intent intentse = new Intent(this,SearchActivity.class);
                startActivity(intentse);
                break;

            case R.id.btn_bo_nho:
                Intent intent = new Intent(this,SecondActivity.class);

                File file = new File(rootPath);
                File[] files = file.listFiles();
                for(int i = 0 ; i < files.length; i ++){
                    mItemViews.add(new ItemView(files[i].getPath(), false,false));
                }
                intent.putExtra("list",mItemViews);
                startActivity(intent);
//                File file = new File(rootPath);
//                File[] files =file.listFiles();
//                for (int i = 0 ; i < files.length;i ++){
//                    itemViews.add(new ItemView(files[i].getPath(),false,false));
//                }
//                adapterRecyclerView = new AdapterRecyclerView(this,itemViews);

                break;
            case R.id.btn_nhat_ky:
                mItemViews = new ArrayList<>();
                Intent intent1 = new Intent(this,SecondActivity.class);
                File file1 = new File(rootPath +"/Download");
                File[] files1 = file1.listFiles();
                for(int i = 0 ; i < files1.length; i ++){
                    mItemViews.add(new ItemView(files1[i].getPath(), false,false));
                }
                intent1.putExtra("list",mItemViews);
                startActivity(intent1);
                break;
            case R.id.btn_hinh_anh:
                itemViews = new ArrayList<>();
                listFileHinhAnh(rootPath,itemViews);
                Intent intent2 = new Intent(this,SecondActivity.class);
                intent2.putExtra("list",itemViews);
                startActivity(intent2);
                break;
            case R.id.btn_tai_lieu:
                itemViews = new ArrayList<>();
                listFileTaiLieu(rootPath, itemViews);
                Intent intent3 = new Intent(this,SecondActivity.class);
                intent3.putExtra("list",itemViews);
                startActivity(intent3);
                break;
            case R.id.btn_am_thanh:
                itemViews = new ArrayList<>();
                listFileAmThanh(rootPath, itemViews);
                Intent intent4 = new Intent(this,SecondActivity.class);
                intent4.putExtra("list",itemViews);
                startActivity(intent4);
                break;
            case R.id.btn_video:
                itemViews = new ArrayList<>();
                listFileVideo(rootPath, itemViews);
                Intent intent5 = new Intent(this,SecondActivity.class);
                intent5.putExtra("list", itemViews);
                startActivity(intent5);
                break;

            default:
                break;

        }
    }
    private void listFileVideo(String path,ArrayList<ItemView> dm){
        File file = new File(path);
        File[] files = file.listFiles();
        for ( int i = 0; i <files.length; i++) {

            String a = files[i].getPath();

            if(a.endsWith(".mp4")){
                dm.add(new ItemView(a,false,false));
            }
            if ( files[i].isDirectory() ) {
                listFileVideo(files[i].getPath(), dm);
            }
        }
    }
    private void listFileHinhAnh(String path,ArrayList<ItemView> dm){
        File file = new File(path);
        File[] files = file.listFiles();
        for ( int i = 0; i <files.length; i++) {

            String a = files[i].getPath();

            if(a.endsWith(".jpg") || a.endsWith(".png")){
                dm.add(new ItemView(a,false,false));
            }
            if ( files[i].isDirectory() ) {
                listFileHinhAnh(files[i].getPath(), dm);
            }
        }
    }

    private void listFileTaiLieu(String path,ArrayList<ItemView> dm){
        File file = new File(path);
        File[] files = file.listFiles();
        for ( int i = 0; i <files.length; i++) {

            String a = files[i].getPath();

            if(a.endsWith(".txt") || a.endsWith(".doc") || a.endsWith("docx") || a.endsWith(".pdf") ){
                dm.add(new ItemView(a,false,false));
            }
            if ( files[i].isDirectory() ) {
                listFileTaiLieu(files[i].getPath(), dm);
            }
        }
    }
    private void listFileAmThanh(String path,ArrayList<ItemView> dm){
        File file = new File(path);
        File[] files = file.listFiles();
        for ( int i = 0; i <files.length; i++) {

            String a = files[i].getPath();

            if(a.endsWith(".mp3") || a.endsWith(".ogg")  || a.endsWith(".m4a") ){
                dm.add(new ItemView(a,false,false));
            }
            if ( files[i].isDirectory() ) {
                listFileAmThanh(files[i].getPath(), dm);
            }
        }
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences pre =getSharedPreferences(prefname,MODE_PRIVATE);
//        SharedPreferences.Editor editor=pre.edit();
//        ArrayList<ItemView> itemViews = (ArrayList<ItemView>) AdapterRVHistory.getInstane().getmItemViews();
//        StringBuffer buffer = new StringBuffer();
//        for(int i = 0 ; i < itemViews.size() ; i ++){
//            buffer.append(itemViews.get(i).getFileName()+ "#");
//        }
//        String str = buffer.toString();
//        editor.putString("list",str);
//        editor.commit();
//    }
//    String prefname="my_data";
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPreferences pre=getSharedPreferences
//                (prefname, MODE_PRIVATE);
//        String str =  pre.getString("list", "");
//        str = str.trim();
//        String list[] = str.split("#");
//        ArrayList<ItemView> itemViews = new ArrayList<>();
//        for(int i = 0 ; i < list.length ; i ++){
//            itemViews.add(new ItemView(list[i],false,false));
//        }
//        AdapterRVHistory.getInstane().setmItemViews(itemViews);
//        AdapterRVHistory.getInstane().notifyDataSetChanged();
//
//    }
}
