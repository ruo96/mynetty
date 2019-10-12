package com.wrh.thread.CompletableFutureTest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:10 2019/10/9 0009
 * @Modified By:
 */
@Slf4j
public class TestCompletableFuture {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            // 模拟执行耗时任务
            System.out.println("任务开始...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 告诉completableFuture任务已经完成
            completableFuture.complete("ok");
        }).start();
        // 获取任务结果，如果没有完成会一直阻塞等待
        String result = completableFuture.get();
        System.out.println("计算结果:" + result);

    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            // 模拟执行耗时任务
            System.out.println("任务开始...");
            try {
                Thread.sleep(3000);
                int i = 1/0;
            } catch (Exception e) {
                // 告诉completableFuture任务发生异常了
                completableFuture.completeExceptionally(e);
            }
            // 告诉completableFuture任务已经完成
            completableFuture.complete("ok");
        }).start();
        // 获取任务结果，如果没有完成会一直阻塞等待
        String result = completableFuture.get();
        System.out.println("计算结果:" + result);


    }


    /**
     * 其中supplyAsync用于有返回值的任务，runAsync则用于没有返回值的任务。Executor参数可以手动指定线程池，
     * 否则默认ForkJoinPool.commonPool()系统级公共线程池，
     * 注意：这些线程都是Daemon线程，主线程结束Daemon线程不结束，只有JVM关闭时，生命周期终止。
     *
     * JDK CompletableFuture 自带多任务组合方法allOf和anyOf
     * allOf是等待所有任务完成，构造后CompletableFuture完成
     * anyOf是只要有一个任务完成，构造后CompletableFuture就完成
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        // 结果集
        List<String> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        CompletableFuture[] cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
                        .thenApply(h->Integer.toString(h))
                        .whenComplete((s, e) -> {
                            System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(cfs).join();
        System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));


    }

    public int calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName()
                    + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Test
    public void test3(){
        CompletableFuture.supplyAsync(()->1).thenApply(i->i+1).thenApply(i->i*i).whenComplete((r,e)-> {
            log.info("===> thread: {}",Thread.currentThread().getName());
            log.info("===> r: {}",r);
        });

        CompletableFuture.supplyAsync(()-> "Hello").thenApply(s->s+" World").thenApply(String::toUpperCase)
            .whenComplete((s,e)->{
                log.info("===> s: {} main : {}",s,Thread.currentThread().getName());
            });

        CompletableFuture.supplyAsync(()-> "Hello").thenApply(s->s+" World").thenApply(String::toUpperCase)
                .whenCompleteAsync((s,e)->{
                    log.info("===> s: {}  other: {}",s,Thread.currentThread().getName());
                });
    }

    /**
     * 这种写法比较简单
     */
    @Test
    public void test4(){
        CompletableFuture.supplyAsync(()->"Hello")
                .thenApply(s->s+" World")
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture("Java"),(s1,s2)->s1+s2)
                .thenAccept((s)->{
                    log.info("===> s: {}  ----------- thread-name: {}",s,Thread.currentThread().getName());
                });


    }

    /**
     * 事实上，如果每个操作都很简单的话（比如：上面的例子中按照id去查）没有必要用这种多线程异步的方式，因为创建线程还需要时间，还不如直接同步执行来得快。
     *
     * 事实证明，只有当每个操作很复杂需要花费相对很长的时间（比如，调用多个其它的系统的接口；
     * 比如，商品详情页面这种需要从多个系统中查数据显示的）的时候用CompletableFuture才合适，不然区别真的不大，还不如顺序同步执行。
     *
     * https://www.cnblogs.com/cjsblog/p/9267163.html
     */
    @Test
    public void test5(){

        String[] list = {"a","b","c"};

        List<CompletableFuture> resList = new ArrayList<>();
        for(String str : list){
            resList.add(CompletableFuture.supplyAsync(()->str).thenApply(e->e.toUpperCase()));
        }

        CompletableFuture.allOf(resList.toArray(new CompletableFuture[resList.size()]))
                .whenComplete((r,e)->{
                    log.info("===> list: {}  ----------- thread-name: {}", JSON.toJSONString(list),Thread.currentThread().getName());
                    if( null == e){
                    }else {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void test6(){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<CompletableFuture> resList = new ArrayList<>();
        for(String str : list){
            resList.add(CompletableFuture.supplyAsync(()->str).thenApply(e->e.toUpperCase()));
        }

        CompletableFuture.allOf(resList.toArray(new CompletableFuture[resList.size()]))
                .whenComplete((r,e)->{
                    log.info("===> list: {}  ----------- thread-name: {}", JSON.toJSONString(list),Thread.currentThread().getName());
                    if( null == e){
                    }else {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * 无返回值和有返回值
     */
    @Test
    public void test7() throws ExecutionException, InterruptedException {

        /**
         * 无返回值
         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("1 run end ...");
        });

        future.get();


        /**
         * 有返回值
         */
        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("2 run end ...");
            return System.currentTimeMillis();
        });

        long time = future1.get();
        System.out.println("time = "+time);
    }

    /**
     * whenComplete 和 whenCompleteAsync 的区别：
     * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
     * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
     */
    @Test
    public void test8() throws InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！");
            }

        });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("执行失败！"+t.getMessage());
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
     */
    @Test
    public void test9() throws ExecutionException, InterruptedException {

        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t*5;
                System.out.println("result2="+result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }

    /**
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
     * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     */
    @Test
    public void testHandle() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                int i= 10/0;
                return new Random().nextInt(10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1; // 默认值
                if(throwable==null){
                    result = param * 2;
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }

    /**
     * 接收任务的处理结果，并消费处理，无返回结果
     * @throws ExecutionException
     * @throws InterruptedException
     * 从示例代码中可以看出，该方法只是消费执行完成的任务，并可以根据上面的任务返回的结果进行处理。并没有后续的输出操作。
     */
    @Test
    public void testAccept() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenAccept(integer -> {
            System.out.println("===>" + integer);
        });
        future.get();
    }

    /**
     * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
     *
     * 该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。只是处理玩任务后，执行 thenAccept 的后续操作。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testThenRun() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();
    }

    /**
     * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
     *
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t+" "+u);
        System.out.println(result.get());
    }

    /**
     * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
     */
    @Test
    public void testThenAcceptBoth()  {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });
        f1.thenAcceptBoth(f2, (t, u) -> System.out.println("f1="+t+";f2="+u+";"));
    }

    /**
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
     */
    @Test
    public void testApplyToEither() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });

        CompletableFuture<Integer> result = f1.applyToEither(f2, t -> {
            System.out.println(t);
            return t * 2;
        });

        System.out.println(result.get());
    }

    /**
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testAcceptEither() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });

        CompletableFuture<Integer> result = f1.applyToEither(f2, t -> {
            System.out.println(t);
            return t * 2;
        });

        System.out.println(result.get());
    }

    /**
     * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testRunAfterBoth() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = 3;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = 2;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });
        f1.runAfterBoth(f2, () -> System.out.println("上面两个任务都执行完成了。"));
    }

    /**
     * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1="+t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param *2;
            System.out.println("t2="+t);
            return t;
        }));
        System.out.println("thenCompose result : "+f.get());
    }


}
