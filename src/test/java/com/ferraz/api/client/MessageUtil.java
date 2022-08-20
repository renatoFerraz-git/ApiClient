package com.ferraz.api.client;

import com.ferraz.api.client.service.impl.ComprovanteServiceImpl;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class MessageUtil {
    //public static ComprovanteServiceImpl comprovante = new ComprovanteServiceImpl();
    //TOKENS
    public static String tokenCatApiValid = "a05cc86b-fcc2-4fa3-a825-56a249cd9e91";
    public static String tokenCatApiInvalid = "a05cc86b-fcc2-4fa3-a825-56a249cd9e19";

    //VOTE

    public static String urlCatApiVote = "https://api.thecatapi.com/v1/votes/";
    public static String bodyCatApiVote = "{\"image_id\": \"8krfAgKYD\",\"value\": \"true\", \"sub_id\": \"demo-ab4d47\"}";

    //FAVOURITIES
    public static String urlCatApiFavourit = "https://api.thecatapi.com/v1/favourites/";
    public static String bodyCatApiFavourite = "{\"image_id\": \"9ccXTANkb\", \"sub_id\": \"your-user-1234\"}";

    //SIGNUP
    public static String urlCatApi = "https://api.thecatapi.com/v1/user/passwordlesssignup";
    public static String bodyCatApi = "{\"email\": \"ouvidoria@nicoleeluisgraficame.com.br\",\"appDescription\": \"TheCatApiApp\"}";

    //MESSAGE
    public static String dubpicateFavourite = "DUPLICATE_FAVOURITE - favourites are unique for account + image_id + sub_id";
    public static String httpErrorMethodNotAllowed = "404 - please consult the documentation for correct url to call. https://docs.thecatapi.com/";


}
