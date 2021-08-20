package com.minkiapps.newspaper.test.article;

import com.bumptech.glide.Glide;
import com.minkiapps.newspaper.test.ResourceTable;
import com.minkiapps.newspaper.test.model.Article;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.List;

public class ArticleItemProvider extends BaseItemProvider {

    private final Context context;
    private final List<Article> items;
    private final ArticleClickedListener articleClickedListener;

    public ArticleItemProvider(final Context context,
                               final List<Article> items,
                               final ArticleClickedListener articleClickedListener) {
        this.context = context;
        this.items = items;
        this.articleClickedListener = articleClickedListener;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).hashCode();
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        if (component == null) {
            component = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_article, null, false);
            component.setTag(new ItemHolder(component));
        }

        final ItemHolder holder = (ItemHolder) component.getTag();
        final Article item = items.get(i);

        holder.preTitle.setText(item.getPreTitle());
        holder.preSubTitle.setText(item.getPreSubTitle());

        Glide.with(context)
                .load(item.getSmallImageUrl())
                .into(holder.image);

        holder.container.setClickedListener(component1 -> articleClickedListener.onArticleItemClicked(item));

        return component;
    }

    public static class ItemHolder {

        final Text preTitle;
        final Text preSubTitle;
        final Image image;
        final Component container;

        public ItemHolder(final Component component) {
            this.container = component.findComponentById(ResourceTable.Id_dl_item_article_container);
            this.preTitle = (Text) component.findComponentById(ResourceTable.Id_t_item_article_pretitle);
            this.preSubTitle = (Text) component.findComponentById(ResourceTable.Id_t_item_article_presubtitle);
            this.image = (Image) component.findComponentById(ResourceTable.Id_i_item_article);
        }
    }
}
