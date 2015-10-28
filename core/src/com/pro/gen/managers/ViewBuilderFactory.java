package com.pro.gen.managers;

import com.pro.gen.utils.ViewID;
import com.pro.gen.views.AppView;
import com.pro.gen.views.BaseView;
import com.pro.gen.views.CreateAccountView;
import com.pro.gen.views.CreateAlienView;
import com.pro.gen.views.EmptyView;
import com.pro.gen.views.GroundView;
import com.pro.gen.views.LandingView;
import com.pro.gen.views.MainMenuView;
import com.pro.gen.views.SplashView;

/**
 * Created by Gallo on 8/11/2015.
 */
public class ViewBuilderFactory {

    public ViewBuilderFactory() {

    }

    public BaseView build(ViewID viewID) {
        switch(viewID) {
            case SPLASH:
                return new SplashView();
            case CREATE_ACCOUNT:
                return new CreateAccountView();
            case CREATE_ALIEN:
                return new CreateAlienView();
            case MAIN_MENU:
                return new MainMenuView();
            case APP:
                return new AppView();
            case LANDING:
                return new LandingView();
            case LAND:
                return new GroundView();
            default:
                return new EmptyView();
        }
    }


}
