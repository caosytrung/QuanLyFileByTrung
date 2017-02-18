package draweralayout.t3h.com.quanlyfilebytrung;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by caotr on 17/08/2016.
 */
public class BoNhoActivity extends Activity {
    private SeekBar seekBar;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_nho);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable = (long)stat.getBlockSize() *(long)stat.getBlockCount();
        long megAvailable = bytesAvailable / 1048576;
        int a =(int) (megAvailable * 100 / (16 * 1024));
        int e= 1024;

        int total = 16 * 1024;
        seekBar.getThumb().mutate().setAlpha(0);
        seekBar.setProgress(a);
        seekBar.setSecondaryProgress(100);
        textView = (TextView) findViewById(R.id.tv_no_nho);
        textView.setText("Bộ nhớ hiện tại " + "(" +megAvailable  + "/"  +total + "  MB)");


    }
}
