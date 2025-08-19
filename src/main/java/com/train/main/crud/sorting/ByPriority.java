package com.train.main.crud.sorting;

import com.train.main.entieties.Issue;

public class ByPriority implements IssueSorting{

    @Override
    public int compare(Issue o1, Issue o2) {
        if(o1.getPriority().ordinal() > o2.getPriority().ordinal()) return 1;
        return 0;
    }
}
