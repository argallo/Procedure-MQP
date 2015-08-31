package com.pro.gen.managers;

import com.pro.gen.utils.ViewID;
import com.pro.gen.views.AppView;
import com.pro.gen.views.BaseView;
import com.pro.gen.views.EmptyView;

/**
 * Created by Gallo on 8/11/2015.
 */
public class ViewBuilderFactory {

    public ViewBuilderFactory() {

    }

    public BaseView build(ViewID viewID) {
        switch(viewID) {
            case APP:
                return new AppView();
            default:
                return new EmptyView();
        }
    }


}
