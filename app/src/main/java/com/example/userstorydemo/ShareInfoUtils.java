package com.example.userstorydemo;

import android.content.Context;

public class ShareInfoUtils {
    private static final ShareInfoUtils shareInfoUtils = new ShareInfoUtils();

    public static ShareInfoUtils getInstance() {
        return shareInfoUtils;
    }

    public ShareInfoUtils() {
    }

    public void setAuthenticationToken(Context context, String authToken) {
        LocalStorage.getInstance().setData(context, "auth_token", authToken);
    }

    public String getAuthenticationToken(Context context) {
        String auth_token = LocalStorage.getInstance().getStringData(context, "auth_token");
        auth_token = auth_token == null ? "" : auth_token;
        return auth_token;
    }

    public void setUserId(Context context, String userid) {
        LocalStorage.getInstance().setData(context, "user_id", userid);
    }

    public String getUserId(Context context) {
        return LocalStorage.getInstance().getStringData(context, "user_id");
    }

    public void setStoreId(Context context, String storeId) {
        LocalStorage.getInstance().setData(context, "store_id", storeId);
    }

    public String getStoreId(Context context) {
        return LocalStorage.getInstance().getStringData(context, "store_id");
    }

    public void removeShareInfo(Context context) {
        LocalStorage.getInstance().clearAll(context);
    }

}
