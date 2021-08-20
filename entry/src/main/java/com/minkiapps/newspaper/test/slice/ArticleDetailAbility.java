package com.minkiapps.newspaper.test.slice;

import com.bumptech.glide.Glide;
import com.minkiapps.newspaper.test.ResourceTable;
import com.minkiapps.newspaper.test.model.Article;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.ScrollView;
import ohos.agp.components.Text;
import ohos.app.Context;

public class ArticleDetailAbility extends Ability {

    public static String EXTRA_ARTICLE_ITEM = "EXTRA_ARTICLE_ITEM";

    @Override
    protected void onStart(final Intent intent) {
        super.onStart(intent);
        setSwipeToDismiss(true);

        setUIContent(ResourceTable.Layout_ability_article_detail);

        final Article item = intent.getSerializableParam(EXTRA_ARTICLE_ITEM);

        final ScrollView scrollView = (ScrollView)findComponentById(ResourceTable.Id_s_ability_article_detail);
        final Image image = (Image)findComponentById(ResourceTable.Id_i_ability_article_detail);
        final Text title = (Text) findComponentById(ResourceTable.Id_t_ability_article_detail_title);
        final Text content = (Text) findComponentById(ResourceTable.Id_t_ability_article_detail_content);

        scrollView.enableScrollBar(Component.AXIS_Y, true);
        scrollView.setMode(Component.OVAL_MODE);
        scrollView.setReboundEffect(true);
        Glide.with(this)
                .load(item.getLargeImageUrl())
                .into(image);

        title.setText(item.getTitle());
        //remove html tags
        content.setText(item.getSummary());
        scrollView.setVibrationEffectEnabled(true);
        scrollView.setTouchFocusable(true);
        scrollView.requestFocus();
    }

    static Intent getIntent(final Context context,
                            final Article article) {
        final Intent i = new Intent();
        i.setParam(EXTRA_ARTICLE_ITEM, article);

        final Operation operation = new Intent.OperationBuilder()
                .withBundleName(context.getBundleName())
                .withAbilityName(ArticleDetailAbility.class.getName())
                .build();

        i.setOperation(operation);
        return i;
    }
}
