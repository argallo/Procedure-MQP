package com.pro.gen.alien;

import com.pro.gen.alien.alienarms.AlienArms;
import com.pro.gen.alien.alienbody.AlienBody;
import com.pro.gen.alien.alienhead.AlienHead;
import com.pro.gen.alien.alienlegs.AlienLegs;

/**
 * Created by Gallo on 9/11/2015.
 */
public interface AlienPlan {

    void setAlienHead(AlienHead alienHead);

    void setAlienBody(AlienBody alienBody);

    void setAlienArms(AlienArms alienArms);

    void setAlienLegs(AlienLegs alienLegs);


}
