package com.stupid.demo.future;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 实战demo
 * 从不同从商城查询某个商品的价格
 */
public class CompletableFutureDemo3 {

    static List<NetMall> netList = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dd"),
            new NetMall("tb")
    );

    public static List<String> getPrice(List<NetMall> list,String productName){
        return list
                .stream()
                .map(netMall ->
                        String.format(productName + "in %s price is %.2f",netMall.getNetMallName(),netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    // List<NetMall> --> List<CompletableFuture<String>> --> List<String>
    public static List<String> getPriceFuture(List<NetMall> list,String productName){
        return list
                .stream()
                .map(netMall ->
                        CompletableFuture.supplyAsync(() -> String.format(productName + "in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(futrue -> futrue.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //List<String> list = getPrice(netList, "xxpp");
        List<String> list = getPriceFuture(netList, "xxpp");
        for (String str:
             list) {
            System.out.println(str);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");

    }
}

@Data
@AllArgsConstructor
class NetMall{
    private String netMallName;
    public double calcPrice(String productName){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.getMessage();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
