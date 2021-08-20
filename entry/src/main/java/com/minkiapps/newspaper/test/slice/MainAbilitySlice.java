package com.minkiapps.newspaper.test.slice;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.minkiapps.newspaper.test.MyApplication;
import com.minkiapps.newspaper.test.ResourceTable;
import com.minkiapps.newspaper.test.article.ArticleClickedListener;
import com.minkiapps.newspaper.test.article.ArticleItemProvider;
import com.minkiapps.newspaper.test.model.Article;
import com.minkiapps.newspaper.test.utils.LogUtils;
import com.minkiapps.newspaper.test.utils.UIUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;
import ohos.app.dispatcher.task.Revocable;
import ohos.app.dispatcher.task.TaskPriority;

import java.net.UnknownHostException;
import java.util.List;

import static ohos.agp.components.Component.AXIS_Y;

public class MainAbilitySlice extends AbilitySlice implements ArticleClickedListener {

    private final static String TAG = "MainAbilitySlice";

    private ListContainer listContainer;
    private CircularProgressView progressView;
    private Component errorContainer;
    private Text errorText;

    private Revocable revocable;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        listContainer = (ListContainer)findComponentById(ResourceTable.Id_lc_ability_main);
        listContainer.setMode(Component.OVAL_MODE);
        final int contentOffset = UIUtils.vpTpPixel(this, 80);
        listContainer.setContentOffSet(contentOffset, contentOffset);
        listContainer.setLongClickable(false);
        listContainer.setTouchFocusable(true);

        progressView = (CircularProgressView) findComponentById(ResourceTable.Id_cpv_ability_main);
        errorContainer = findComponentById(ResourceTable.Id_dl_ability_main_error_container);
        errorText = (Text)findComponentById(ResourceTable.Id_t_ability_main_error);

        findComponentById(ResourceTable.Id_t_ability_main_btn_retry).setClickedListener(component -> reload());

        reload();
    }

    private void reload() {
        if(revocable != null) {
            revocable.revoke();
        }

        progressView.setVisibility(Component.VISIBLE);
        errorContainer.setVisibility(Component.HIDE);
        listContainer.setVisibility(Component.HIDE);
        revocable = getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(() -> {
            try {
                initListOnUIThread(MyApplication.getApiInteractor().loadArticles());
            } catch (Exception e) {
                LogUtils.e(TAG, "Failed to load articles: " + e.getMessage());
                showErrorUI(e);
            }
        });
    }

    private void showErrorUI(final Exception exception) {
        getUITaskDispatcher().asyncDispatch(() -> {
            if(exception instanceof UnknownHostException) {
                errorText.setText(ResourceTable.String_error_offline);
            } else {
                errorText.setText(ResourceTable.String_error_generic);
            }
            progressView.setVisibility(Component.HIDE);
            errorContainer.setVisibility(Component.VISIBLE);
            listContainer.setVisibility(Component.HIDE);
        });
    }

    private void initListOnUIThread(final List<Article> articleItems) {
        getUITaskDispatcher().asyncDispatch(() -> {
            progressView.setVisibility(Component.HIDE);
            errorContainer.setVisibility(Component.HIDE);
            listContainer.setVisibility(Component.VISIBLE);
            listContainer.setItemProvider(new ArticleItemProvider(this, articleItems, this));
            listContainer.requestFocus();
            listContainer.enableScrollBar(AXIS_Y, true);
        });
    }

    @Override
    public void onArticleItemClicked(final Article article) {
        startAbility(ArticleDetailAbility.getIntent(this, article));
    }

    @Override
    protected void onStop() {
        super.onStop();
        revocable.revoke();
    }
}
