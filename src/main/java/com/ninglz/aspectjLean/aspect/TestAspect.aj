package com.ninglz.aspectjLean.aspect;

import com.ninglz.aspectjLean.model.Test;

public aspect  TestAspect {

    pointcut TestCut(Test test):call(* com.ninglz.aspectjLean.model.Test.show()) && target(test);;

    before(Test test): TestCut(test) {
        System.out.println("调用方法前:"+test);
    }

    void around(Test test): TestCut(test) {
        System.out.println("环绕前");
        proceed(test);
        System.out.println("环绕后");

    }

    after(Test test): TestCut(test) {
        System.out.println("调用方法后:"+test);
    }
}
