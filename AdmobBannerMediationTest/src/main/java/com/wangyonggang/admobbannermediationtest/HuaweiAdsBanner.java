package com.wangyonggang.admobbannermediationtest;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.banner.BannerView;

public class HuaweiAdsBanner implements CustomEventBanner{
    /** The SampleAdView representing a banner ad. */
    private BannerView bannerView ;

    /** The event is being destroyed. Perform any necessary cleanup here. */
    @Override
    public void onDestroy() {
        if (bannerView != null) {
            bannerView.destroy();
        }
    }

    /**
     * The app is being paused. This call is only forwarded to the adapter if
     * the developer notifies AdMob mediation that
     * the app is being paused.
     */
    @Override
    public void onPause() {
        // The sample ad network doesn't have an onPause method, so does nothing.
    }

    /**
     * The app is being resumed. This call is only forwarded to the
     * adapter if the developer notifies AdMob
     * mediation that the app is being resumed.
     */
    @Override
    public void onResume() {
        // The sample ad network doesn't have an onResume method, so does nothing.
    }

    @Override
    public void requestBannerAd(Context context,
                                CustomEventBannerListener listener,
                                String serverParameter,
                                AdSize size,
                                MediationAdRequest mediationAdRequest,
                                Bundle customEventExtras) {

        bannerView = new BannerView(context);

        // Assumes that the serverParameter is the AdUnit for the Sample Network.
       bannerView.setAdId(serverParameter);

        bannerView.setBannerAdSize(new BannerAdSize(size.getWidth(), size.getHeight()));

        // Implement a SampleAdListener and forward callbacks to AdMob.
        // The callback forwarding is handled by SampleBannerEventForwarder.
      bannerView.setAdListener(new SampleCustomBannerEventForwarder(listener, bannerView));

        //AdParam adParam = new AdParam.Builder().build();
        // Make an ad request.
        //bannerView.loadAd(adParam);
        bannerView.loadAd(createSampleRequest(mediationAdRequest));

    }
    private AdParam createSampleRequest(MediationAdRequest mediationAdRequest) {
        AdParam request = new AdParam.Builder().build();
        //SampleAdRequest request = new SampleAdRequest();

        return request;
    }


}
