package com.pro.gen.managers;

import com.pro.gen.utils.ViewID;
import com.pro.gen.views.BaseView;
import com.pro.gen.views.BattleView;
import com.pro.gen.views.BossBattleView;
import com.pro.gen.views.BossView;
import com.pro.gen.views.CapsuleView;
import com.pro.gen.views.CreateAccountView;
import com.pro.gen.views.CreateStarterPlanetView;
import com.pro.gen.views.EmptyView;
import com.pro.gen.views.ExploreView;
import com.pro.gen.views.LeaderboardView;
import com.pro.gen.views.MainMenuView;
import com.pro.gen.views.PlanetView;
import com.pro.gen.views.PlanetlistView;
import com.pro.gen.views.ShopView;
import com.pro.gen.views.SolarSystemView;
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
                return new CreateStarterPlanetView();
            case MAIN_MENU:
                return new MainMenuView();
            case EXPLORE:
                return new ExploreView();
            case SOLAR_SYSTEM:
                return new SolarSystemView();
            case PLANET:
                return new PlanetView();
            case BATTLE:
                return new BattleView();
            case CAPSULE:
                return new CapsuleView();
            case LEADERBOARD:
                return new LeaderboardView();
            case PLANETLIST:
                return new PlanetlistView();
            case BOSS:
                return new BossView();
            case STORE:
                return new ShopView();
            case BOSS_BATTLE:
                return new BossBattleView();
            default:
                return new EmptyView();
        }
    }


}
