package com.ddd.toy.pet.salon.domain.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionGroup {
    private String optionGroupNm;

    private Long optionGroupSn;

    private int sortOrder;

    @Builder.Default
    List<OptionArticle> articleList = new ArrayList<>();

    public OptionGroup(String name, long sn, int sortOrder) {
        this.optionGroupNm = name;
        this.optionGroupSn = sn;
        this.sortOrder = sortOrder;
    }

    private OptionGroup from() {
        return OptionGroup.builder()
                .optionGroupNm(optionGroupNm)
                .optionGroupSn(optionGroupSn)
                .sortOrder(sortOrder)
                .build();
    }

    public void addArticles(List<String> articleArr, List<OptionGroup> groups, List<OptionArticle> articles) {
        StringBuilder key = new StringBuilder(articleArr.get(0));
        for (String articleId : articleArr) {
            int idx = articleArr.indexOf(articleId);
            String fullKey = createFullKey(articleArr, key, idx);
            int sortOrder = idx + 1;

            OptionArticle article = findAddArticle(articles, articleId, fullKey);
            OptionGroup group = findGroup(groups, sortOrder);

            if(!article.isEmpty()) {
                addGroupAndArticle(article, group);
            }
        }
    }

    private void addGroupAndArticle(OptionArticle article, OptionGroup group) {
        addGroupInfo(group);
        addArticle(group, article);
    }

    private OptionGroup findGroup(List<OptionGroup> groups, int i) {
        return groups.stream()
                .filter(g -> g.eqSortOrder(i))
                .findFirst()
                .map(OptionGroup::from)
                .orElseGet(OptionGroup::new);
    }



    private static OptionArticle findAddArticle(List<OptionArticle> articles, String articleId, String fullKey) {
        return articles.stream()
                .filter(a -> a.eqArticleId(articleId))
                .findFirst()
                .map(a -> a.from(fullKey))
                .orElseGet(OptionArticle::new);
    }

    public void addGroupInfo(OptionGroup group) {
        if (this.sortOrder == 0) {
            this.sortOrder = group.sortOrder;
            this.optionGroupSn = group.optionGroupSn;
            this.optionGroupNm = group.optionGroupNm;
        }
    }

    public void addArticle(OptionGroup group, OptionArticle article) {
        if (!eqSortOrder(article.getSortOrder())) {
            // 다른 그룹
            // 항목에서 하위 항목으로 추가
            addSubArticle(group, article);
        } else if(!isContainArticle(article.getArticleId())) {
            // 같은 그룹
            // 없는 항목이면 추가
            this.articleList.add(article);
        }
    }

    private void addSubArticle(OptionGroup group, OptionArticle article) {
        this.articleList.stream()
                .filter(a -> a.eqFullKey(article))
                .forEach(a -> a.callAddSubArticle(group, article));
    }

    private boolean eqSortOrder(int idx) {
        return sortOrder == idx;
    }

    private boolean isContainArticle(String articelId) {
        return this.articleList.stream().anyMatch(a -> a.eqArticleId(articelId));
    }

    private static String createFullKey(List<String> articleArr, StringBuilder key, int idx) {
        return idx == 0 ? key.toString() : key.append("_").append(articleArr.get(idx)).toString();
    }

}
