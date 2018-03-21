package codekiller.me.modelapp.PolyValley;


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
public class ProjectFragment extends Fragment {
    private WebView webView;

    public ProjectFragment() {
        // Required empty public constructor
    }

    public static ProjectFragment newInstance(){
        return new ProjectFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        webView = view.findViewById(R.id.project_web_view);
        webView.loadUrl("file:///android_asset/poly_valley.html");

        return view;
    }

}
