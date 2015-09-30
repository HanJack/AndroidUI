package com.hsj.libeffects;


import com.hsj.libeffects.core.BaseEffects;
import com.hsj.libeffects.core.FadeIn;
import com.hsj.libeffects.core.Fall;
import com.hsj.libeffects.core.FlipH;
import com.hsj.libeffects.core.FlipV;
import com.hsj.libeffects.core.NewsPaper;
import com.hsj.libeffects.core.RotateBottom;
import com.hsj.libeffects.core.RotateLeft;
import com.hsj.libeffects.core.Shake;
import com.hsj.libeffects.core.SideFall;
import com.hsj.libeffects.core.SlideBottom;
import com.hsj.libeffects.core.SlideLeft;
import com.hsj.libeffects.core.SlideRight;
import com.hsj.libeffects.core.SlideTop;
import com.hsj.libeffects.core.Slit;

/**
 * Created by lee on 2014/7/30.
 */
public enum  Effectstype {

    FADEIN(FadeIn.class),
    SLIDELEFT(SlideLeft.class),
    SLIDETOP(SlideTop.class),
    SLIDEBOTTOM(SlideBottom.class),
    SLIDERIGHT(SlideRight.class),
    FALL(Fall.class),
    NEWSPAGER(NewsPaper.class),
    FLIPH(FlipH.class),
    FLIPV(FlipV.class),
    ROTATEBOTTOM(RotateBottom.class),
    ROTATELEFT(RotateLeft.class),
    SLIT(Slit.class),
    SHAKE(Shake.class),
    SIDEFILL(SideFall.class);
    private Class effectsClazz;

    private Effectstype(Class mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        try {
            return (BaseEffects) effectsClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
