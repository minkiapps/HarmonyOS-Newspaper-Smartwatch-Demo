package com.minkiapps.newspaper.test.network;

import com.minkiapps.newspaper.test.model.Article;
import com.minkiapps.newspaper.test.model.Articles;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class ApiInteractorImpl {

    private final ApiService apiService;

    public ApiInteractorImpl(final ApiService apiService) {
        this.apiService = apiService;
    }

    public List<Article> loadArticles() throws Exception {
        final Response<Articles> response = apiService.queryArticles().execute();

        if(response.isSuccessful()) {
            final Articles body = response.body();
            return body.getArticleList();
        }else {
            throw new IOException(String.format("Failed to translate Error code %d Error Message %s", response.code(), response.message()));
        }
    }
}
