package codekiller.me.modelapp.PolyBrand;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChinaPolyFragment extends Fragment {
    private WebView webView;

    public ChinaPolyFragment() {
        // Required empty public constructor
    }

    public static ChinaPolyFragment newInstance(){
        return new ChinaPolyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_china_poly, container, false);

        webView = view.findViewById(R.id.china_web_view);
        webView.loadUrl("file:///android_asset/china_poly.html");

        return view;
    }

}
