package com.wangyonggang.admobbannermediationtest;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;
import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.banner.BannerView;

public class SampleCustomBannerEventForwarder extends AdListener {
    private CustomEventBannerListener mBannerListener;
    private BannerView mAdView;

    /**
     * Creates a new {@code SampleBannerEventForwarder}.
     * @param listener A {@link CustomEventBannerListener} that should receive
     *                 forwarded events.
     * @param adView   A {@link }.
     */
    public SampleCustomBannerEventForwarder(
            CustomEventBannerListener listener, BannerView adView) {
        this.mBannerListener = listener;
        this.mAdView = adView;
    }

    @Override
    public void onAdLoaded(){
        mBannerListener.onAdLoaded(mAdView);
    }

    @Override
    public void onAdFailed(int errorCode) {
        switch (errorCode) {
            case 0:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
                break;
            case 1:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INVALID_REQUEST);
                break;
            case 2:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_NETWORK_ERROR);
                break;
            case 3:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_NO_FILL);
                break;
        }
    }

    @Override
    public void onAdOpened() {
        mBannerListener.onAdClicked();
        mBannerListener.onAdOpened();
    }
    @Override
    public void onAdClicked() {
        // 广告点击时调用
        mBannerListener.onAdClicked();

    }
    @Override
    public void onAdClosed() {
        mBannerListener.onAdClosed();
    }
}