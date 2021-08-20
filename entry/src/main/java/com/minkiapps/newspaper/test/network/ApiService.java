package com.minkiapps.newspaper.test.network;

import com.minkiapps.newspaper.test.model.Articles;
import retrofit2.Call;

public interface ApiService {

    //TODO implement your API here
    Call<Articles> queryArticles();
}
