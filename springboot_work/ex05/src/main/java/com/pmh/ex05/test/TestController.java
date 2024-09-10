package com.pmh.ex05.test;

import com.pmh.ex05.obj.AA;
import com.pmh.ex05.obj.BB;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    // 스프링컨테이너 직접 관리 하는 방식...
    private final AA aa;
    private final BB bb;

    // JVM 직접 관리를 하고
    // AA aa = new AA();

    @GetMapping("aa")
    public String aa(){
        aa.doA();
        return "aaaaaa";
    }

    @PostMapping("bb")
    public String bb(){
        bb.doB();
        return "bbbbbb";
    }

}

