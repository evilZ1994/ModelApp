package codekiller.me.modelapp.PolyBrand;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PolyEstateFragment extends Fragment {
    private WebView webView;

    public PolyEstateFragment() {
        // Required empty public constructor
    }

    public static PolyEstateFragment newInstance(){
        return new PolyEstateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poly_estate, container, false);

        webView = view.findViewById(R.id.estate_web_view);
        WebSettings webSettings = webView.getSettings();
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webView.loadUrl("file:///android_asset/poly_estate.html");

        return view;
    }

}
