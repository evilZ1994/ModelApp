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
public class PolyCDFragment extends Fragment {
    private WebView webView;

    public PolyCDFragment() {
        // Required empty public constructor
    }

    public static PolyCDFragment newInstance(){
        return new PolyCDFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poly_cd, container, false);

        webView = view.findViewById(R.id.cd_web_view);
        webView.loadUrl("file:///android_asset/poly_cd.html");

        return view;
    }

}
