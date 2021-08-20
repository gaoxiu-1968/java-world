package com.zhangmingke.aop.servieImpl;

import com.zhangmingke.aop.service.Buy;
import org.springframework.stereotype.Service;

@Service
public class Boy implements Buy {
    @Override
    public void buyThing() {
        System.out.println("男孩买东西！");
    }
}
