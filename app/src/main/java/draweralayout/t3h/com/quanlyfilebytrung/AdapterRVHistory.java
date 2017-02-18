package draweralayout.t3h.com.quanlyfilebytrung;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by caotr on 16/08/2016.
 */
public class AdapterRVHistory extends RecyclerView.Adapter<AdapterRVHistory.RecyclerViewHoler> {
    private static AdapterRVHistory instane;
    private static Context mContext;
    private static ArrayList<ItemView> mItemViews;

    public static AdapterRVHistory getInstane(){
        if(instane == null){
            instane = new AdapterRVHistory(mContext,mItemViews);
            return instane;
        }
        else {
            return instane;
        }
    }

    private AdapterRVHistory(Context context,ArrayList<ItemView> itemViews){
        mContext = context;
        mItemViews = itemViews;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<ItemView> getmItemViews() {
        return mItemViews;
    }

    public void setmItemViews(ArrayList<ItemView> mItemViews) {
        this.mItemViews = mItemViews;
    }

    @Override
    public RecyclerViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_rv_lich_su,parent,false);
        return new RecyclerViewHoler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHoler holder, int position) {
        String str = mItemViews.get(position).getFileName();

        for(int i = str.length() - 1; i > 0 ; i -- ){
            if(str.charAt(i) == '/'){
                str = str.substring(i + 1, str.length());
                break;
            }
        }
        holder.tv.setText(str);
        final String path = mItemViews.get(position).getFileName();
        String type = path.substring(path.length() - 3, path.length());
        switch (type) {
            case "jpg":
                Picasso.with(mContext).
                        load(new File(mItemViews.get(position).getFileName())).
                        placeholder(R.drawable.load).resize(128,128).into(holder.iv);
                break;
            case "png":
                Picasso.with(mContext).
                        load(new File(mItemViews.get(position).getFileName())).
                        placeholder(R.drawable.load).resize(128,128).into(holder.iv);
                break;
            case "mp3":
                holder.iv.setImageResource(R.drawable.ic_music);
                break;
            case "txt":
                holder.iv.setImageResource(R.drawable.ic_txt);
                break;
            case "rar":
                holder.iv.setImageResource(R.drawable.ic_rar);
                break;
            case "zip":
                holder.iv.setImageResource(R.drawable.ic_rar);
                break;
            case "m4a":
                holder.iv.setImageResource(R.drawable.ic_m4a);
                break;
            case "mp4":
                holder.iv.setImageResource(R.drawable.ic_folder);
                break;
            case "ogg":
                holder.iv.setImageResource(R.drawable.ic_ogg);
                break;
            case "pdf":
                holder.iv.setImageResource(R.drawable.ic_pdf);
                break;
            case "doc":
                holder.iv.setImageResource(R.drawable.ic_doc);
                break;
            case "ocx":
                holder.iv.setImageResource(R.drawable.ic_doc);
                break;
            case "xml":
                holder.iv.setImageResource(R.drawable.ic_xml);

            default:
                holder.iv.setImageResource(R.drawable.ic_folder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItemViews.size();
    }

    public class RecyclerViewHoler extends RecyclerView.ViewHolder {
        public TextView tv;
        public ImageView iv;
        public RecyclerViewHoler(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_lich_su);
            iv = (ImageView) itemView.findViewById(R.id.iv_lich_su);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    File file = new File(mItemViews.get(position).getFileName());
                    String filePath1 = mItemViews.get(position).getFileName();
                    openFile(file, filePath1.substring(filePath1.length() - 4, filePath1.length()));
                }
            });
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
}
