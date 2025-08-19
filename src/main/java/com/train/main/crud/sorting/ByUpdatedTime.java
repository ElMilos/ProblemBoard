package com.train.main.crud.sorting;

import com.train.main.entieties.Issue;

public class ByUpdatedTime implements IssueSorting{

    @Override
    public int compare(Issue o1, Issue o2) {
        if (o1.getUpdatedAt().compareTo(o2.getUpdatedAt()) < 0) return 1;
        return 0;
    }
}
