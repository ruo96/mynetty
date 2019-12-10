package com.wrh.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:13 2019/12/6 0006
 * @Modified By:
 */
@Slf4j
@RestController
public class KafkaProducer {

    /*@Autowired
    private KafkaTemplate<Object,Object> template;

    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input){
        template.send("topic", input);
    }*/
}
