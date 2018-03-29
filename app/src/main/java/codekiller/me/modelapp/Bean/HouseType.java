package codekiller.me.modelapp.Bean;

import android.net.Uri;

/**
 * Created by Lollipop on 2018/3/24.
 */

public class HouseType {
    private String title;
    private Uri imgUri;

    public HouseType(String title, Uri imgUri) {
        this.title = title;
        this.imgUri = imgUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getImgUri() {
        return imgUri;
    }

    public void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
    }
}
