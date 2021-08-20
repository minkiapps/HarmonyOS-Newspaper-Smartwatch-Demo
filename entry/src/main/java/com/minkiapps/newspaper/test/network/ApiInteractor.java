package com.minkiapps.newspaper.test.network;

import com.minkiapps.newspaper.test.model.Article;

import java.util.List;

public interface ApiInteractor {

    List<Article> loadArticles() throws Exception;
}
