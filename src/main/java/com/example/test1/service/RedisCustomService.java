package com.example.test1.service;

import com.example.test1.dto.Product;
import com.example.test1.dto.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisCustomService {

    private final RedisProductService redisProductService;

    public void insertReply(Product product) {
        Reply reply = new Reply();
        reply.setId(product.getProductDesc());
        reply.setValue("Redis Test");
        reply.setProduct(product);
        redisProductService.create(reply);
    }

    public String read(String id) {
        return redisProductService.read(id).toString();
    }

    //@ToDo : 아래 로직을 Redis Notification, 실시간 알림 로직 등을 검토해서 수정 필요
    //대용량 처리시에 아래 로직은 문제 발생 가능성 높음
    public void waitReply(String id) {
        boolean success = false;

        while (!success) {
            try {
                System.out.println(redisProductService.read(id));
                success = true;
            } catch(Exception e) {
                System.out.println("RETRY - ID : " + id);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
