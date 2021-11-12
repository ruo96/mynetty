package com.wrh.thread.CompletableFutureTest;

import com.alibaba.fastjson.JSON;
import com.wrh.list.TestList;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static cn.hutool.core.lang.Validator.isUpperCase;
import static org.junit.Assert.*;

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
     *          而且能够捕捉到异常并且自定义返回值， 和exceptionally（）方法不同的一点是， 无论发没发生异常，都会调用
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

    /** 20种用法*/

    /** 1、 创建一个完成的CompletableFuture*/
    @Test
    public void Test550() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        System.out.println("cf.isDone() = " + cf.isDone());
        System.out.println(cf.getNow(null));

    }
    
    @Test
    public void Test568() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase
                /*delayed*/);
        CompletableFuture exceptionHandler = cf.handle((s, th) -> { return (th != null) ? "message upon cancel" : ""; });
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch(CompletionException ex) { // just for testing
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }

        assertEquals("message upon cancel", exceptionHandler.join());
        
    }
    
    @Test
    public void Test586() {
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s));
        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                s -> s + " from applyToEither");
//        assertTrue(cf2.join().endsWith(" from applyToEither"));
        System.out.println("cf2.join() = " + cf2.join());

    }

    @SneakyThrows
    private String delayedLowerCase(String s) {
        TimeUnit.SECONDS.sleep(1);
        return s.toLowerCase();
    }

    @SneakyThrows
    private String delayedUpperCase(String s) {
        TimeUnit.SECONDS.sleep(2);
        s = s.toUpperCase();
        return s;
    }

    @Test
    public void Test608() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));
        System.out.println("result = " + result);


    }

    /** 13.用Biconsumer接收两个stage的结果*/
    @Test
    public void Test620() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        System.out.println(result);

    }

    /** 14.将Bifunction同时作用于两个阶段的结果*/
    @Test
    public void Test632() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        System.out.println(cf.getNow(null));

    }
    
    @Test
    public void Test643() {
        cars().thenCompose(cars -> {
            List<CompletionStage> updatedCars = cars.stream()
                    .map(car -> rating(car.getId()).thenApply(r -> {
                        car.setRate(r);
                        return car;
                    })).collect(Collectors.toList());
            CompletableFuture done = CompletableFuture
                    .allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));
            return done.thenApply(v -> updatedCars.stream().map(CompletionStage::toCompletableFuture)
                    .map(CompletableFuture::join).collect(Collectors.toList()));
        }).whenComplete((cars, th) -> {
            if (th == null) {
                System.out.println("cars = " + cars);
            } else {
                throw new RuntimeException((Throwable) th);
            }
        }).toCompletableFuture().join();

        
    }

    @Test
    public void Test557() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s->{
            return s.toUpperCase();
        });

        System.out.println(cf.getNow(null));

    }

    @Test
    public void Test567() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s->{
            assert (Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        assert (cf.getNow(null) ==null);
        assert ("MESSAGE".equals(cf.join()));

    }

    /**
     * 如果下一阶段接收了当前阶段的结果，但是在计算的时候不需要返回值(它的返回类型是void)，
     * 那么它可以不应用一个函数，而是一个消费者， 调用方法也变成了thenAccept
     */
    @Test
    public void Test583() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("then accpet message").thenAccept(s->result.append(s));
        System.out.println(result);

        CompletableFuture cf = CompletableFuture.completedFuture("then2").thenApplyAsync(s->result.append(s));
        cf.join();
        System.out.println(result);

    }

    /**
     * 在两个阶段都执行完后运行一个 Runnable
     *
     * 这个例子演示了依赖的CompletableFuture如果等待两个阶段完成后执行了一个Runnable。
     * 注意下面所有的阶段都是同步执行的，第一个阶段执行大写转换，第二个阶段执行小写转换。
     */
    @Test
    public void Test599() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        result.append(original);
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done")
        );

        System.out.println(result);

    }

    /**
     * 1、变换结果
     * 这些方法的输入是上一个阶段计算后的结果，返回值是经过转化后结果
     */
    @Test
    public void Test619() {
        String result = CompletableFuture.supplyAsync(()->{return "Hello:";}).thenApplyAsync(v->addWorld(v)).join();
        System.out.println(result);

    }

    private String addWorld(String v) {
        return v + "World";
    }

    /**
     * 2、消费结果
     * 这些方法只是针对结果进行消费，入参是Consumer，没有返回值
     */
    @Test
    public void Test633() {
        CompletableFuture.supplyAsync(()->{return "Hello ";}).thenAccept(v->{
            System.out.println("Consumer: " + v);
        });
        CompletableFuture.supplyAsync(()->{return "Hello ";}).thenAcceptAsync(v->{
            System.out.println("Consumer: " + v);
        });
    }

    /**
     * 3、结合两个CompletionStage的结果，进行转化后返回
     * 需要上一阶段的返回值，并且other代表的CompletionStage也要返回值之后，把这两个返回值，进行转换后返回指定类型的值。
     *
     * 说明：同样，也存在对两个CompletionStage结果进行消耗的一组方法，例如thenAcceptBoth，这里不再进行示例。
     * 这个可以用来汇聚大的接口
     */
    @Test
    public void Test648() {
        String result = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        }),(s1,s2)->{return s1+":"+s2;}).join();
        System.out.println(result);
    }

    /**
     * 4、两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的处理
     * 两种渠道完成同一个事情，就可以调用这个方法，找一个最快的结果进行处理，最终有返回值。
     */
    @Test
    public void Test675() {
        long start = System.currentTimeMillis();
        String result = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy!";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Girl!";
        }), (s)->{return s;}).join();
        long end = System.currentTimeMillis();

        System.out.println(result + " cost:" + (end-start)+"ms");
    }

    /**
     * 5、运行时出现了异常，可以通过exceptionally进行补偿
     *
     */
    @Test
    public void Test702() {
        String result = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(true){
                throw new RuntimeException("exception test!");
            }
            return "Hi Boy";
        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return "Hello world!";
        }).join();
        System.out.println(result);
    }

    private static List<String> videos = Arrays.asList("夏洛特","春物","某科学的超电磁炮","秒速五厘米","言叶之庭","星之声","徒然喜欢你","just because","天气之子","你的名字");

    public static List<Video> selectVideos() {
        List<Video> videoList = videos.stream().map(name->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Video(name);
        }).collect(Collectors.toList());
        return videoList;
    }

    private static Executor executor =
            Executors.newFixedThreadPool(
                    // 当视频数量小于100的时候取视频数量，大于100的时候取100
                    Math.min(videos.size(), 100),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            // 将线程设置为守护线程
                            t.setDaemon(true);
                            return t;
                        }
                    });
    public static List<Video> selectVideosPerfect() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<CompletableFuture<Video>> futureList = videos.stream()
                // 将name映射为CompletableFuture<Video>
                .map(name -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return new Video(name);
                    //汇总CompletableFuture<Video>
                    // 使用重载方法传入自定义executor
                },executor)).collect(Collectors.toList());
        return futureList.stream()
                // 将CompletableFuture<Video>中取出Video
                .map(CompletableFuture::join)
                // 汇总为Video列表
                .collect(Collectors.toList());
    }

    /**
     * 使用join来聚合异步请求 好东西
     */
    @Test
    public void Test775() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfect");
        selectVideosPerfect();
        stopWatch.stop();
        stopWatch.start("normal");
        selectVideos();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

    public static void main1(String[] args) throws InterruptedException {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });

        TimeUnit.SECONDS.sleep(2);

        assertEquals("MESSAGE", cf.getNow(null));
        System.out.println("cf.getNow(null) = " + cf.getNow(null));
    }

    public static void main2(String[] args) {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().getName());
            assertTrue(Thread.currentThread().getName().startsWith("Thread-0"));
            System.out.println(Thread.currentThread().isDaemon());
            assertTrue(Thread.currentThread().isDaemon());
            try {
                randomSleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executor);

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    private static void randomSleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
    
    @Test
    public void Test828() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));
        assertTrue("Result was empty", result.length() > 0);
        System.out.println("result = " + result);

    }
    
    @Test
    public void Test840() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        assertEquals("MESSAGEmessage", result.toString());
        System.out.println("result = " + result);
    }
    
    @Test
    public void Test851() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));
        
    }

    @Test
    public void Test861() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(String::toLowerCase),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.join());

    }

    @Test
    public void Test872() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(String::toLowerCase)
                        .thenApply(s -> upper + s));
        System.out.println("cf.getNow() = " + cf.getNow(null));
        assertEquals("MESSAGEmessage", cf.join());

    }

    /**
     * 下面的例子演示了当任意一个CompletableFuture完成后， 创建一个完成的CompletableFuture.
     * 待处理的阶段首先创建， 每个阶段都是转换一个字符串为大写。因为本例中这些阶段都是同步地执行(thenApply),
     * 从anyOf中创建的CompletableFuture会立即完成，这样所有的阶段都已完成，
     * 我们使用whenComplete(BiConsumer<? super Object, ? super Throwable> action)处理完成的结果。
     */
    @Test
    public void Test883() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(String::toUpperCase))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if(th == null) {
                assertTrue(isUpperCase((String) res));
                result.append(res);
            }
        });
        assertTrue("Result was empty", result.length() > 0);
        
    }

    /**
     * 上一个例子是当任意一个阶段完成后接着处理，接下来的两个例子演示当所有的阶段完成后才继续处理, 同步地方式和异步地方式两种。
     */
    @Test
    public void Test907() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(String::toUpperCase))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> assertTrue(isUpperCase((CharSequence) cf.getNow(null))));
            result.append("done");
        });
        assertTrue("Result was empty", result.length() > 0);
        
    }

    /**
     * 使用thenApplyAsync()替换那些单个的CompletableFutures的方法，allOf()会在通用池中的线程中异步地执行。所以我们需要调用join方法等待它完成。
     */
    @Test
    public void Test925() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(String::toUpperCase))
                .collect(Collectors.toList());
        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> assertTrue(isUpperCase((CharSequence) cf.getNow(null))));
                    result.append("done");
                    System.out.println(result);
                });
        allOf.join();
        assertTrue("Result was empty", result.length() > 0);
        
    }
    
    @Test
    public void Test946() {


        cars().thenCompose(cars -> {
            List<CompletionStage> updatedCars = cars.stream()
                    .map(car -> rating(car.getId()).thenApply(r -> {
                        car.setRate(r);
                        return car;
                    })).collect(Collectors.toList());
            updatedCars.forEach(e->e.toCompletableFuture().getNow(null));

            CompletableFuture done = CompletableFuture
                    .allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));
            return done.thenApply(v -> updatedCars.stream().map(CompletionStage::toCompletableFuture)
                    .map(CompletableFuture::join).collect(Collectors.toList()));
        }).whenComplete((cars, th) -> {
            if (th == null) {
//                cars.forEach(System.out::println);
                System.out.println(cars);
            } else {
                System.out.println(th);
                throw new RuntimeException();
            }
        }).toCompletableFuture().join();
        
    }

    private CompletionStage<Integer> rating(Integer id) {
        CompletableFuture futures = CompletableFuture.completedFuture(id*100);
        return  futures;
    }

    private CompletionStage<List<Car>> cars() {
        List<Car> messages = new ArrayList<>();
        Car c1 = new Car();
        c1.setName("w1");
        c1.setManufactureId(1);
//        c1.setRate(1);
        c1.setId(1);

        Car c2 = new Car();
        c2.setName("w2");
        c2.setManufactureId(2);
//        c2.setRate(2);
        c2.setId(2);

        messages.add(c1);
        messages.add(c2);

        CompletableFuture futures = CompletableFuture.completedFuture(messages);
        return  futures;
    }

    @Test
    public void Test1101() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            return "test";
        });

        String result = future.get();
        System.out.println("result = " + result);

    }
    
    @Test
    public void Test1112() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 10)
                .thenApplyAsync((e) -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    return e * 10;
                }).thenApplyAsync(e -> e - 1)
                .whenComplete((r, e)->{
                    System.out.println("done");
                })
                ;

        cf.join();
        System.out.println(cf.get());
        
    }

    @Test
    public void Test1133() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<String> list = TestList.getStringList();
        final String[] ef = {};

        /*List<CompletableFuture<String>> futureList = list
                .stream()
                .map(request->
                        CompletableFuture.supplyAsync(e->{
                            //some opts
                            ef[0] = e + "-done";
                            return ef[0];
                        },executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allCF = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));

        allCF.join();*/

    }
    
    @Test
    public void Test1157() throws ExecutionException, InterruptedException {
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s));
        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                s -> s + " from applyToEither");
//        assertTrue(cf2.join().endsWith(" from applyToEither"));
        cf2.join();
        System.out.println("cf2.get() = " + cf2.get());

    }

    @Test
    public void Test1171() throws ExecutionException, InterruptedException {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        /*CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));*/

        CompletableFuture.completedFuture(original).thenApply(s->s.toLowerCase()).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(s->s.toUpperCase()),
                () -> result.append("done"));

        assertTrue("Result was empty", result.length() > 0);
        System.out.println("result = " + result);
        System.out.println("original = " + original);

    }

    @Test
    public void Test1189() {
        /**
         * thenApply如果以Async结尾，就会异步执行，通过forkjoinpool来实现，使用守护线程来执行任务。
         */
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s->{
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });

        assertEquals("MESSAGE", cf.getNow(null));

    }

    @Test
    public void Test1202() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s->{
            assertTrue(Thread.currentThread().isDaemon());
            try {
                randomSleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());

    }

    static ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    @Test
    public void Test1219() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
                executor);
        CompletableFuture exceptionHandler = cf.handle((s, th) -> { return (th != null) ? "message upon cancel" : ""; });
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch(CompletionException ex) { // just for testing
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }

        assertEquals("message upon cancel", exceptionHandler.join());

    }
    
    @Test
    public void Test1246() {
        for (int i = 0; i < 100; i++) {
            String original = "Message";
            CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                    .thenApplyAsync(s -> delayedUpperCase(s));
            CompletableFuture cf2 = cf1.applyToEither(
                    CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                    s -> s + "abc");
                    //s -> s + " from applyToEither");
            System.out.println("cf2.join().toString() = " + cf2.join().toString());
            //assertTrue(cf2.join().toString().endsWith(" from applyToEither"));
        }


    }
    
    @Test
    public void Test1263() {
        // 或者是大写，或者是小写， 后面+
        for (int i = 0; i < 10; i++) {
            String original = "Message";
            StringBuilder result = new StringBuilder();
            CompletableFuture cf = CompletableFuture.completedFuture(original)
                    .thenApplyAsync(s -> delayedUpperCase(s))
                     //这下面是整体和上面做either接收参数
                     .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                            s -> result.append(s).append("+acceptEither"));
            cf.join();
            //assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
            System.out.println("result.toString() = " + result.toString());
        }
        
    }
    
    @Test
    public void Test1281() {
        String original = "Message";
        StringBuilder change= new StringBuilder();
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAccept(s-> System.out.println("now original is :"+ s)),
                () -> result.append(original).append("+done"));
        System.out.println("result = " + result);
        System.out.println("original = " + original);

    }
    
    @Test
    public void Test1294() {
        // 前后两个stage都执行完毕之后会执行合并操作  并且使用thenApply 或者 thenApplyAsync 必须要等了
        long start = System.currentTimeMillis();
        String original = "Message";
        StringBuilder result = new StringBuilder();
        //CompletableFuture cf =

                CompletableFuture.completedFuture(original).thenApplyAsync(s-> delayedUpperCase(s) )
                        .thenAcceptBoth(
                        CompletableFuture.completedFuture(original).thenApplyAsync(s->delayedLowerCase(s)),
                        (s1, s2) -> result.append(s1 +" === "+ s2)).join();
        //cf.join();
        System.out.println(result.toString());
        long end = System.currentTimeMillis();

        System.out.println("cost:  end - start = " + (end - start));
    }
    
    @Test
    public void Test1317() {
        // 将Bifunction同时作用于两个阶段的结果  todo  现在还搞不太清楚和上面用法的区别，感觉都差不多  combine是对cf本身的改变
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        cf.join();
        System.out.println("cf.getNow(null) = " + cf.getNow(null));

    }
    
    @Test
    public void Test1330() {
        //可以使用thenCompose来完成前两个例子中的操作
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
                        .thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
        
    }
    
    @Test
    public void Test1341() {
        //当多个阶段中有有何一个完成，即新建一个完成阶段
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if(th == null) {
                assertTrue(isUpperCase((String) res));
                result.append(res);
            }
        });
        assertTrue("Result was empty", result.length() > 0);
        
    }
    
    @Test
    public void Test1359() {
        // 当所有的阶段完成，新建一个完成阶段
        /*StringBuilder result = new StringBuilder();
        List messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow("null").toString())));
                    result.append("done");
                });
        assertTrue("Result was empty", result.length() > 0);*/
        
    }
    
    @Test
    public void Test1376() {
        //当所有阶段完成以后，新建一个异步完成阶段
        /*StringBuilder result = new StringBuilder();
        List messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());
        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow("null").toString())));
                    result.append("done");
                });
        allOf.join();
        assertTrue("Result was empty", result.length() > 0);*/
        
    }

    @Test
    public void Test1394() {

        cars().thenCompose(cars-> {
            List<CompletionStage> updatedCars = cars.stream()
                    .map(car -> rating(car.getManufactureId()).thenApply(r -> {
                        car.setRating(r);
                        return car;
                    })).collect(Collectors.toList());
            CompletableFuture done = CompletableFuture
                    .allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));
            return done.thenApply(v -> updatedCars.stream().map(CompletionStage::toCompletableFuture)
                    .map(CompletableFuture::join).collect(Collectors.toList()));
        }).whenComplete((cars, th) -> {
            if (th == null) {
                //(List<Car>)cars.fo
                System.out.println("cars.toString() = " + cars.toString());
            } else {
                //throw new RuntimeException(th);
            }
        }).toCompletableFuture().join();

    }




}
