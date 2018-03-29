package codekiller.me.modelapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;

import codekiller.me.modelapp.Zoomable.ZoomableDraweeView;

public class ImageViewActivity extends Activity {
    private ZoomableDraweeView zoomableDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        zoomableDraweeView = findViewById(R.id.img);

        Intent intent = getIntent();
        Uri imgUri = Uri.parse(intent.getStringExtra("image_uri"));

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imgUri)
                .build();
        zoomableDraweeView.setController(controller);
    }
}
