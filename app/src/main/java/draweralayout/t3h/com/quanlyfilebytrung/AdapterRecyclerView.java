package draweralayout.t3h.com.quanlyfilebytrung;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caotr on 16/08/2016.
 */
public class AdapterRecyclerView extends
        RecyclerView.Adapter<AdapterRecyclerView.RecyclerViewHoler> {
    private Context mContext;
    private ArrayList<ItemView> mItemViews;
    private ArrayList<String> mNames;
    private IDeleteFile mIDeleteFile;
    public void setIDeleteFile(IDeleteFile iDeleteFile){
        mIDeleteFile = iDeleteFile;
    }

    public List<ItemView> getmItemViews() {
        return mItemViews;
    }

    public AdapterRecyclerView(Context context, ArrayList<ItemView> itemViews) {
        mContext = context;
        mItemViews = itemViews;
        mNames = new ArrayList<>();
//        for(int i = 0 ; i < mItemViews.size() ; i++){
//            String fileName = mItemViews.get(i).getFileName();
//            for(int  j = fileName.length() - 1 ; j > 0; j --){
//                if(fileName.indexOf(j) == '/'){
//                    fileName = fileName.substring(j , fileName.length());
//                    break;
//                }
//            }
//            mNames.add(fileName);
//        }
    }

    @Override
    public RecyclerViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_view, parent, false);

        return new RecyclerViewHoler(view);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHoler holder, final int position) {
        if(holder.checkBox.isChecked()){
            mItemViews.get(position).setCheck(true);
        }

        String str = mItemViews.get(position).getFileName();

        for(int i = str.length() - 1; i > 0 ; i -- ){
            if(str.charAt(i) == '/'){
                str = str.substring(i + 1, str.length());
                break;
            }
        }
        holder.tvName.setText(str);
        if (mItemViews.get(position).isShowCheckBox()) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        final String path = mItemViews.get(position).getFileName();
        String type = path.substring(path.length() - 3, path.length());
        switch (type) {
            case "jpg":
                Picasso.with(mContext).
                        load(new File(mItemViews.get(position).getFileName())).
                        placeholder(R.drawable.load).resize(128,128).into(holder.ivShow);
                break;
            case "png":
                Picasso.with(mContext).
                        load(new File(mItemViews.get(position).getFileName())).
                        placeholder(R.drawable.load).resize(128,128).into(holder.ivShow);
                break;
            case "mp3":
                holder.ivShow.setImageResource(R.drawable.ic_ogg);
                break;
            case "txt":
                holder.ivShow.setImageResource(R.drawable.ic_txt);
                break;
            case "rar":
                holder.ivShow.setImageResource(R.drawable.ic_rar);
                break;
            case "zip":
                holder.ivShow.setImageResource(R.drawable.ic_rar);
                break;
            case "m4a":
                holder.ivShow.setImageResource(R.drawable.ic_m4a);
                break;
            case "mp4":
                holder.ivShow.setImageResource(R.drawable.ic_folder);
                break;
            case "ogg":
                holder.ivShow.setImageResource(R.drawable.ic_ogg);
                break;
            case "pdf":
                holder.ivShow.setImageResource(R.drawable.ic_pdf);
                break;
            case "exe":
                holder.ivShow.setImageResource(R.drawable.ic_exe);
                break;
            case "apk":
                holder.ivShow.setImageResource(R.drawable.ic_apk);
                break;
            case "doc":
                holder.ivShow.setImageResource(R.drawable.ic_doc);
                break;
            case "ocx":
                holder.ivShow.setImageResource(R.drawable.ic_doc);
                break;
            case "xml":
                holder.ivShow.setImageResource(R.drawable.ic_xml);

            default:
                holder.ivShow.setImageResource(R.drawable.ic_folder);
                break;
        }

//        holder.tvName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File file = new File(mItemViews.get(position).getFileName());
//                if(file.isDirectory()){
////                    File[] files = file.listFiles();
////                    List<ItemView> itemViews = new ArrayList<>();
////                    for(int i = 0 ; i < files.length; i ++){
////                        itemViews.add(new ItemView(files[i].getPath(),false,false));
////                    }
////                    mItemViews = itemViews;
////                    notifyDataSetChanged();
//                    SecondActivity secondActivity = new SecondActivity();
//                    Intent intent = new Intent(mContext,secondActivity.getClass());
//                    Bundle bundle = new Bundle();
//                    bundle.putString("root",file.getPath());
//                    intent.putExtras(bundle);
//                    mContext.startActivity(intent);
//
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mItemViews.size();
    }

    public void deleFiles(){
        for(int i = 0 ; i < mItemViews.size() ; i ++){

            if (mItemViews.get(i).isCheck()){
                mItemViews.remove(i);
                File file = new File(mItemViews.get(i).getFileName());
                file.delete();

                notifyItemRemoved(i);
                notifyItemRangeChanged(i, mItemViews.size());
            }
        }
    }

    private void openFile(File file, String exFile){
        Intent intent;
        switch(exFile) {
            case ".txt":
               intent = new Intent();
               intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "text/plain");
                mContext.startActivity(intent);
                break;
        case ".xml":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "text/xml");
                mContext.startActivity(intent);
                break;
            case ".apk":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
                mContext.startActivity(intent);
                break;
            case ".png":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "image/png");
                mContext.startActivity(intent);
                break;
            case ".jpg":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "image/jpeg");
                mContext.startActivity(intent);
                break;
            case ".mp4":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "video/mp4");
                mContext.startActivity(intent);
                break;
            case ".rar":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/x-rar-compressed");
                mContext.startActivity(intent);
                break;
            case ".zip":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/x-rar-compressed");
                mContext.startActivity(intent);
                break;
            case ".mp3":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "audio/mp3");
                mContext.startActivity(intent);
                break;
            case ".m4a":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "audio/m4a");
                mContext.startActivity(intent);
                break;
            case ".ogg":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "audio/ogg");
                mContext.startActivity(intent);
                break;
            case ".doc":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/msword");
                mContext.startActivity(intent);
                break;
            case "docx":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/msword");
                mContext.startActivity(intent);
                break;
            case ".pdf":
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                mContext.startActivity(intent);
                break;

            }

    }
    boolean isShow = false;

    public boolean getShow(){
        return isShow;
    }

    public class RecyclerViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CheckBox checkBox;
        public ImageView ivShow;
        public TextView tvName;

        public RecyclerViewHoler(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_item);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_item);
            tvName = (TextView) itemView.findViewById(R.id.tv_item);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemViews.get(getPosition()).setCheck(checkBox.isChecked());
                }
            });

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    for (int i = 0 ; i < mItemViews.size() ; i ++){
                        mItemViews.get(i).setShowCheckBox(true);
                    }
                    notifyDataSetChanged();
                    mIDeleteFile.show(true);
                    return false;
                }
            });
        }



        @Override
        public void onClick(View v) {
            int position = getPosition();
            File file = new File(mItemViews.get(position).getFileName());
            String filePath1 = mItemViews.get(position).getFileName();
            File[] files1 = file.listFiles();



                if (file.isDirectory()) {
//                    File[] files = file.listFiles();
//                    List<ItemView> itemViews = new ArrayList<>();
//                    for(int i = 0 ; i < files.length; i ++){
//                        itemViews.add(new ItemView(files[i].getPath(),false,false));
//                    }
//                    mItemViews = itemViews;
//                    notifyDataSetChanged();
                    if (files1
                            .length == 0 ){
                        mContext.startActivity(new Intent(mContext, EmptyActivity.class));
                    }else{
                        SecondActivity secondActivity = new SecondActivity();
                        Intent intent = new Intent(mContext, secondActivity.getClass());
                        File file1 = new File(file.getPath());
                        ArrayList<ItemView> list = new ArrayList<>();
                        File[] files = file1.listFiles();
                        for(int i = 0 ; i < files.length; i ++){
                            list.add(new ItemView(files[i].getPath(), false,false));
                        }
                        intent.putExtra("list", list);
                        mContext.startActivity(intent);
                    }


                }
                else {
                    AdapterRVHistory adapterRVHistory = AdapterRVHistory.getInstane();
                    ArrayList<ItemView> itemViews = adapterRVHistory.getmItemViews();
                    itemViews.add(new ItemView(filePath1, false, false));
                    adapterRVHistory.setmItemViews(itemViews);
                    adapterRVHistory.setmItemViews(itemViews);
                    adapterRVHistory.notifyDataSetChanged();
                    openFile(file, filePath1.substring(filePath1.length() - 4, filePath1.length()));
                }

        }
    }


}



