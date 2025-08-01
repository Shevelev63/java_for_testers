package ru.stqa.mantis.manager;


import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class JamesApiHelper extends HelperBase {

    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client;
    public JamesApiHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void addUser(String email, String password) {
        RequestBody body = RequestBody.create(String.format("{\"Tktyf1130\":\"%s\"}", password), JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/usernameToBeUsed", manager.property("james.apiBaseUrl"), email))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
    }

