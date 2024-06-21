package com.ddd.toy.pet.salon.domain.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionArticle {
    private String articleNm;

    private String articleId;

    private int sortOrder;

    private String fullKey;

    @Builder.Default
    private OptionGroup optionGroup = new OptionGroup();

    public OptionArticle(String name, String id, int sortOrder) {
        this.articleNm = name;
        this.articleId = id;
        this.sortOrder = sortOrder;
    }

    public OptionArticle from(String fullKey) {
        return OptionArticle.builder()
                .articleNm(articleNm)
                .articleId(articleId)
                .sortOrder(sortOrder)
                .fullKey(fullKey)
                .build();
    }

    public boolean eqArticleId(String articelId) {
        return this.articleId.equals(articelId);
    }

    public boolean eqFullKey(OptionArticle article) {
        return article.getFullKey().contains(fullKey);
    }

    public void  callAddSubArticle(OptionGroup group, OptionArticle article) {
        if(optionGroup == null) optionGroup = new OptionGroup();

        // 하위 항목 추가
        addGroupAndArticle(group, article);
    }

    private void addGroupAndArticle(OptionGroup group, OptionArticle article) {
        optionGroup.addGroupInfo(group);
        optionGroup.addArticle(group, article);
    }

    public boolean isEmpty() {
        return articleId == null;
    }
}
