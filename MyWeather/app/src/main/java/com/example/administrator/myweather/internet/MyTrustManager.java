package com.example.administrator.myweather.internet;

import com.example.administrator.myweather.constant.Constants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by zhengwuy on 2017/12/29.
 * email: 13802885114@139.com
 * des:https证书处理
 */

class MyTrustManager {
    private X509Certificate sX509Certificate;
    private SSLContext mSSLContext;
    private X509TrustManager mX509TrustManager;

    private void init() {
        if (sX509Certificate == null) {
            InputStream inputStream = null;
            try {
                inputStream = new ByteArrayInputStream(Constants.ApiConstant.WEATHER_HTTPS_CER.getBytes("utf-8"));
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                sX509Certificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);
            } catch (IOException | CertificateException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    MyTrustManager() {
        init();
        try {
            String ketStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(ketStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("cert", sX509Certificate);
            String algorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(algorithm);
            trustManagerFactory.init(keyStore);
            mSSLContext = SSLContext.getInstance("SSL");
            mSSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
            mX509TrustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
        } catch (KeyStoreException | KeyManagementException | IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }

    SSLContext getSSLContext() {
        return mSSLContext;
    }

    X509TrustManager getX509TrustManager() {
        return mX509TrustManager;
    }
}
