package draweralayout.t3h.com.quanlyfilebytrung;

import java.io.Serializable;

/**
 * Created by caotr on 16/08/2016.
 */
public class ItemView implements Serializable {
    private String fileName;
    private int igShow;
    private boolean showCheckBox;
    private boolean check;

    public ItemView(String fileName, boolean showCheckBox, boolean check) {
        this.fileName = fileName;
        this.showCheckBox = showCheckBox;
        this.check = false;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIgShow() {
        return igShow;
    }

    public void setIgShow(int igShow) {
        this.igShow = igShow;
    }

    public boolean isShowCheckBox() {
        return showCheckBox;
    }

    public void setShowCheckBox(boolean showCheckBox) {
        this.showCheckBox = showCheckBox;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
