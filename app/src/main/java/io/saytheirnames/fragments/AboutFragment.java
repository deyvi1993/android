package io.saytheirnames.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import io.saytheirnames.R;
import io.saytheirnames.utils.CustomTabUtil;

public class AboutFragment extends Fragment {

    private View myFragment;

    String twitterLink = "https://twitter.com/SayTheirName_io";
    String slackLink = "https://t.co/JvU3sgfoFu";
    String repoLink = "https://github.com/Say-Their-Name/";
    String addNameLink = "https://airtable.com/shr6Da28410B2Muy3";
    String aboutPageLink = "https://saytheirnames.io/about";

    private Button btnTwitter, btnSlack, btnRepo, btnRequest;

    /*private WebView webView;
    private ProgressBar progressBar;
    private WebSettings webSettings;*/
    private Toolbar toolbar;


    public static AboutFragment newInstance() {
        return new AboutFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_about, container, false);

       /* webView = myFragment.findViewById(R.id.webview_content);
        progressBar = myFragment.findViewById(R.id.progressBar);*/

        toolbar = myFragment.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

/*        webView.loadUrl(aboutPageLink);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });*/

        btnRepo = myFragment.findViewById(R.id.btnViewRepo);
        btnTwitter = myFragment.findViewById(R.id.btnFollowUs);
        btnSlack = myFragment.findViewById(R.id.btnSlackChannel);
        btnRequest = myFragment.findViewById(R.id.btnRequestToAdd);

        btnRepo.setOnClickListener(v -> {
            visitPages(repoLink);
        });
        btnSlack.setOnClickListener(v -> {
            visitPages(slackLink);
        });
        btnTwitter.setOnClickListener(v -> visitPages(twitterLink));
        btnRequest.setOnClickListener(v -> visitPages(addNameLink));

        return myFragment;
    }

    private void visitPages(String link) {
        CustomTabUtil.openCustomTabForUrl(requireActivity(), link);
    }

}