public class webapp extends Activity {
    WebView mainWebView;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webapp);
        readContacts();

        mainWebView = (WebView) (findViewById(R.id.mainWebView));
        mainWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mainWebView.getSettings().setLoadsImagesAutomatically(true);
        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.getSettings().setUserAgentString("Mozilla/5.0(Linux; Android 4.4.4; XT1080 Build/SU6-7.3)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
        name = mainWebView.getSettings().getUserAgentString();



        mainWebView.loadUrl("http://headsupcore-dev.ngrok.com/schedule/");



    }
    @Override
    public void onBackPressed(){
        if(mainWebView.canGoBack()){
            mainWebView.goBack();
        }

        else{
            super.onBackPressed();
        }
    }
    
    //insert completed class for contactdataconversion to JSON
    
    }
