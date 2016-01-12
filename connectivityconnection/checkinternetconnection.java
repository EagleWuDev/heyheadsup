public boolean checkInternetConnection()
    {
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null) {
            NetworkInfo mobile = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wyfy = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wyfy.getState() == NetworkInfo.State.CONNECTED || mobile.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            } else{
            }

        } return false;
    }
    
  
NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.mipmap.ic_notification_error)
        .setContentTitle("Internet not Detected")
        .setContentText("Internet definitely not detected");
