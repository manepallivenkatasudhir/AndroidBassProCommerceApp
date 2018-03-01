package com.example.manep.android_bassprocommerceapp.https;

import android.content.Context;

import com.example.manep.android_bassprocommerceapp.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by dpaunovi on 2/12/18.
 */

public class HttpsUtil {

    public enum Supported_HTTP_METHODS {GET, POST}

    ;
    private static SSLContext sslContext;

    public static void InitializeKeyStore(Context ctx) {
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }

// Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance(keyStoreType);

            keyStore.load(null, null);

            //TODO different CERT for PROD
            keyStore.setCertificateEntry("ca", getCertificate(cf, ctx, R.raw.bpunonprod));
            //keyStore.setCertificateEntry("ca", getCertificate(cf, ctx, R.raw.commonservicescert)); //keystores can have more than one cert in them.


// Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

// Create an SSLContext that uses our TrustManager
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static Certificate getCertificate(CertificateFactory cf, Context ctx, int resourceId) throws CertificateException {
        InputStream caInput = new BufferedInputStream(ctx.getResources().openRawResource(resourceId));
        try {
            assert cf != null;
            return cf.generateCertificate(caInput);
        } finally {
            try {
                caInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static HttpsURLConnection getConnection(String url) {


        try {
            HttpsURLConnection retval = (HttpsURLConnection) new URL(url).openConnection();


            //TODO for prime time this needs tested and protected against null
            retval.setSSLSocketFactory(sslContext.getSocketFactory());

            //TODO remove the hostname verifier if we can get BP to name the non-prod cert to match http address.
            //below remarked as dangerous if we do not control the other host, fortunatly
            //our cross functional partners do
            retval.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    HostnameVerifier hv =
                            HttpsURLConnection.getDefaultHostnameVerifier();
                    if (hv.verify(hostname, session))
                        return true;
                    else
                        return hv.verify("Dummy Certificate", session);

                    // TODO and we'll need to remove this from the prod version.
                }
            });

            return retval;

        } catch (IOException e) {
            e.printStackTrace();
            return null; //TODO return null or throw exception
        }
    }


    public static void applyGenericGetHeaders(HttpsURLConnection conn) {

        conn.setRequestProperty("Accept", "application/json");
    }

    public static void applyGenericPostHeaders(HttpsURLConnection conn) {
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
            //TODO only on typos (once) and if POST is no longer supported by HTTP
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Accept", "*/*");

        // Send post request
        conn.setReadTimeout(20000);
        conn.setDoInput(true);
    }
}