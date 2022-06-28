package com.example.movieapp.model;

import java.io.Serializable;
import java.util.List;

public class AllCategory implements Serializable {
    private String categoryTitle;
    private int categoryID;

    private List<CategoryItem> categoryItemList = null;

    public AllCategory(int categoryID, String categoryTitle, List<CategoryItem> categoryItemList) {
        this.categoryTitle = categoryTitle;
        this.categoryID = categoryID;
        this.categoryItemList = categoryItemList;
    }

    public List<CategoryItem> getCategoryItemList() {
        return categoryItemList;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
