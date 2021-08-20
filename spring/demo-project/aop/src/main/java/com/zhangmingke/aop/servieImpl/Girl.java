package com.zhangmingke.aop.servieImpl;

import com.zhangmingke.aop.service.Buy;
import org.springframework.stereotype.Service;

@Service
public class Girl implements Buy {
    @Override
    public void buyThing() {
        System.out.println("女孩买东西！");
    }
}
