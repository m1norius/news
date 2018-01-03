package com.minorius.news.module.activity_module;

import java.util.List;

/**
 * Created by Minorius on 22.12.2017.
 */

public interface Communicator {
    void addToRecycler(List response);
    void clearList();
    void notifyAdapter();
}
