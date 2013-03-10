package com.edgark.carousel;

/**
 * Created with IntelliJ IDEA.
 * User: edgar
 * Date: 3/5/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ScrollViewListener {

    void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    void onScrollEnded();
}
