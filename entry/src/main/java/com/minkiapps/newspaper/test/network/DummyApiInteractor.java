package com.minkiapps.newspaper.test.network;

import com.minkiapps.newspaper.test.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyApiInteractor implements ApiInteractor{
    @Override
    public List<Article> loadArticles() throws Exception {
        final List<Article> articles = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 20; i++) {
            final Article article = new Article();
            final int index = random.nextInt(255);
            article.setSmallImageUrl(String.format("https://picsum.photos/id/%s/300/150",index));
            article.setLargeImageUrl(String.format("https://picsum.photos/id/%s/600/300",index));
            article.setPreTitle("tristique sollicitudin amet");
            article.setPreSubTitle("pretium lectus quam id leo in vitae turpis massa sed");
            article.setTitle("tristique sollicitudin nibh sit amet");
            article.setSummary("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Commodo odio aenean sed adipiscing diam donec adipiscing tristique risus. In ante metus dictum at tempor commodo ullamcorper a lacus. Ut consequat semper viverra nam. Velit aliquet sagittis id consectetur purus ut faucibus pulvinar elementum. Dapibus ultrices in iaculis nunc. Neque convallis a cras semper auctor neque vitae tempus. Tristique senectus et netus et malesuada fames. Scelerisque eleifend donec pretium vulputate sapien. Sed pulvinar proin gravida hendrerit lectus. Accumsan in nisl nisi scelerisque.");
            articles.add(article);
        }

        return articles;
    }
}
