package com.diabin.latteec.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018/6/24.
 */

public enum EcIcons implements Icon{
    icon_scan('\ue668'),
    icon_ali_pay('\ue642');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
