/* Copyright (c) 2019 ZUM Internet, Inc.
 * All right reserved.
 * http://www.zum.com
 * This software is the confidential and proprietary information of ZUM
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with ZUM.
 *
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2019-01-22
 */
package com.zummore.effectivejava.joonghyun;


import com.zummore.effectivejava.joonghyun.equals.EqualsFailClazz;
import com.zummore.effectivejava.joonghyun.equals.EqualsSuccessClazz;
import com.zummore.effectivejava.joonghyun.generic.Collections;
import com.zummore.effectivejava.joonghyun.generic.SubA;
import com.zummore.effectivejava.joonghyun.generic.SubB;
import com.zummore.effectivejava.joonghyun.generic.Super;
import com.zummore.effectivejava.joonghyun.pizza.Calzone;
import com.zummore.effectivejava.joonghyun.pizza.NyPizza;
import com.zummore.effectivejava.joonghyun.pizza.Pizza;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Joonghyun {

    @Test
    public void 피자를_이렇게_만들수도_있구나() {
        Pizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .builder();

        Pizza calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM)
                .sauceInside()
                .builder();
    }

    @Test
    public void 타임스템프_데이트_비교() {
        Date date = new Date(); //Date 상위객체
        Timestamp stamp = new Timestamp(date.getTime());    //Timestamp 하위객체

        assertTrue(date.equals(stamp)); //date 는 stamp 와 같다.
        assertFalse(stamp.equals(date)); //인스턴스가 stamp 가 아니면 같은객체로 보지 않는다. 맞는말이네

        assertTrue(stamp.compareTo(date) == 0);     //stamp compareTo = 0 으로 date 와 stamp 가 같다.
        assertTrue(date.compareTo(stamp) == 1);     //date compareTo = 1 으로 date 가 stamp 보다 크다.
    }

    @Test
    public void equals_비교조심_해야하지만_성공한다() {
        EqualsFailClazz a = new EqualsFailClazz("joong", 99L);
        EqualsFailClazz b = new EqualsFailClazz("joong", 990L);
        assertTrue(a.equals(b));

        EqualsSuccessClazz c = new EqualsSuccessClazz("joong", 99L);
        EqualsSuccessClazz d = new EqualsSuccessClazz("joong", 990L);
        assertTrue(c.equals(d));
    }

    @Test
    public void 제네릭_super_extends() {
        List<SubA> subAList = new ArrayList<>();
        subAList.add(new SubA("a"));
        subAList.add(new SubA("b"));

        List<SubB> subBList = new ArrayList<>();
        subBList.add(new SubB("1"));
        subBList.add(new SubB("2"));

        List<Super> supers = new ArrayList<>();
        supers.add(new Super("1"));
        supers.add(new Super("2"));

        //supers 에 sub a 를 넣은다.
        Collections.copy(supers, subAList);

        //supers 에 sub a 를 넣은다.
        Collections.copy2(supers, subAList);
    }
}
