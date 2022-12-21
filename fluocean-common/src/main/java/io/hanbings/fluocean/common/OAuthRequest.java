package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.interfaces.Serialization;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OAuthRequest implements Request {
    static SSLContext NOP_TLS_V12_SSL_CONTEXT;
    static HostnameVerifier NOP_HOSTNAME_VERIFIER = (s, sslSession) -> true;

    static X509TrustManager NOP_TRUST_MANAGER = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    static {
        // set https protocols version
        System.setProperty("https.protocols", "TLSv1.2");

        try {
            NOP_TLS_V12_SSL_CONTEXT = SSLContext.getInstance("TLSv1.2");
            NOP_TLS_V12_SSL_CONTEXT.init(null, new TrustManager[]{NOP_TRUST_MANAGER}, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    OkHttpClient client;

    public OAuthRequest() {
        client = new OkHttpClient();
    }

    public OAuthRequest(long timeout, Proxy proxy) {
        client = client(timeout, proxy);
    }

    static OkHttpClient client(long timeout, Proxy proxy) {
        return new OkHttpClient.Builder()
                .sslSocketFactory(
                        new OAuthSSLSocketFactory(proxy, NOP_TLS_V12_SSL_CONTEXT.getSocketFactory()),
                        NOP_TRUST_MANAGER
                )
                .socketFactory(new OAuthSocketFactory(proxy))
                .hostnameVerifier(NOP_HOSTNAME_VERIFIER)
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .addInterceptor(new OkHttpProxyInterceptor(proxy))
                .build();
    }

    @Override
    public Response get(Serialization serialization,
                        @Nullable Proxy proxy, String url) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(1500, proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }

    @Override
    public Response get(Serialization serialization,
                        @Nullable Proxy proxy, String url, Map<String, String> params) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(1500, proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }

    @Override
    public Response post(Serialization serialization,
                         @Nullable Proxy proxy, String url) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(new FormBody(List.of(), List.of()))
                .addHeader("Accept", "application/json")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(1500, proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }

    @Override
    public Response post(Serialization serialization,
                         @Nullable Proxy proxy, String url, Map<String, String> form) {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        form.forEach((k, v) -> {
            keys.add(k);
            values.add(v);
        });

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(new FormBody(keys, values))
                .addHeader("Accept", "application/json")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(1500, proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }

    record OkHttpProxyInterceptor(Proxy proxy) implements Interceptor {
        @Override
        public okhttp3.@NotNull Response intercept(@NotNull Chain chain) throws IOException {
            ThreadLocalProxyAuthenticator.setCredentials(proxy.username(), proxy.password());
            ThreadLocalProxyAuthenticator.clearCredentials();

            return chain.proceed(chain.request());
        }
    }

    static class ThreadLocalProxyAuthenticator extends Authenticator {
        private static final ThreadLocal<PasswordAuthentication> credentials = new ThreadLocal<>();

        public static ThreadLocalProxyAuthenticator getInstance() {
            return SingletonHolder.instance;
        }

        public static void setCredentials(String user, String password) {
            credentials.set(new PasswordAuthentication(user, password.toCharArray()));
        }

        public static void clearCredentials() {
            ThreadLocalProxyAuthenticator authenticator = ThreadLocalProxyAuthenticator.getInstance();
            Authenticator.setDefault(authenticator);
            credentials.set(null);
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return credentials.get();
        }

        private static class SingletonHolder {
            private static final ThreadLocalProxyAuthenticator instance = new ThreadLocalProxyAuthenticator();
        }
    }
}
