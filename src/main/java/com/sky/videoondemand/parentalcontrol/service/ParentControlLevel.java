package com.sky.videoondemand.parentalcontrol.service;

import java.util.HashMap;
import java.util.Map;

public enum ParentControlLevel {

    UNIVERSAL("U", 1),
    PARENTAL_GUIDANCE("PG", 2),
    TWELVE("12", 3),
    FIFTEEN("15", 4),
    EIGHTEEN("18", 5);

    private String contentCategory;

    private Integer contentCategoryLevel;

    public final static Map<String, ParentControlLevel> contentCategoryLevelMap = new HashMap<String, ParentControlLevel>();

    static {
        for (ParentControlLevel parentControlLevel : ParentControlLevel.values()) {
            contentCategoryLevelMap.put(parentControlLevel.contentCategory, parentControlLevel);
        }
    }

    public static ParentControlLevel resolveLevel(String contentCategory) {
        return contentCategoryLevelMap.get(contentCategory);
    }

    ParentControlLevel(String contentCategory, Integer contentCategoryLevel) {
        this.contentCategory = contentCategory;
        this.contentCategoryLevel = contentCategoryLevel;
    }

    public String getContentCategory() {
        return contentCategory;
    }

    public Integer getContentCategoryLevel() {
        return contentCategoryLevel;
    }

}
