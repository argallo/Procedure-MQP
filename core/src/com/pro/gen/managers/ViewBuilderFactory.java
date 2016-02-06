package com.pro.gen.managers;

import com.pro.gen.utils.ViewID;
import com.pro.gen.views.BaseView;
import com.pro.gen.views.CapsuleView;
import com.pro.gen.views.CreateAccountView;
import com.pro.gen.views.CreateAlienView;
import com.pro.gen.views.EmptyView;
import com.pro.gen.views.LandingView;
import com.pro.gen.views.LeaderboardView;
import com.pro.gen.views.MainMenuView;
import com.pro.gen.views.PlanetlistView;
import com.pro.gen.views.SplashView;
import com.pro.gen.views.ArmyView;
import com.pro.gen.views.DestroyView;
import com.pro.gen.views.ExploreView;
import com.pro.gen.views.MineView;
import com.pro.gen.views.PlanetView;
import com.pro.gen.views.SolarSystemView;

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
            case EXPLORE:
                return new ExploreView();
            case SOLAR_SYSTEM:
                return new SolarSystemView();
            case PLANET:
                return new PlanetView();
            case DESTROY:
                return new DestroyView();
            case MINE:
                return new MineView();
            case ARMY:
                return new ArmyView();
            case LANDING:
                return new LandingView();
            case CAPSULE:
                return new CapsuleView();
            case LEADERBOARD:
                return new LeaderboardView();
            case PLANETLIST:
                return new PlanetlistView();
            default:
                return new EmptyView();
        }
    }


}
