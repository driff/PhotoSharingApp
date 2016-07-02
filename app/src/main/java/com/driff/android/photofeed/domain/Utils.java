package com.driff.android.photofeed.domain;

import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by johnj on 29/6/2016.
 */
public class Utils {

    private Geocoder geocoder;
    private final static String GRAVATAR_URL= "http://www.gravatar.com/avatar";

    public Utils(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    public String getAvatarUrl(String email){
        return GRAVATAR_URL + md5(email) + "?s=64";
    }

    public String getFromLocation(double lat, double lng){
        String result= "";
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            Address address= addresses.get(0);
            for(int i = 0; i< address.getMaxAddressLineIndex(); i++){
                result += address.getAddressLine(i) + ",";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
