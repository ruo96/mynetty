package com.wrh.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.*;
import com.wrh.serialize.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.util.Preconditions;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author wuruohong
 * @Classname TestGuava
 * @Description TODO
 * @Date 2020/5/21 15:15
 */
@Slf4j
public class TestGuava {

    private static final Joiner joiner = Joiner.on(',').skipNulls();

    private static final Splitter splitter = Splitter.on(',').trimResults().omitEmptyStrings();

    private static final CharMatcher charMatcher = CharMatcher.DIGIT;

    @Test
    public void Test1() {
        String join = joiner.join(Lists.newArrayList("a",null,"b"));
        log.info("join ={}",join);

        for(String tmp : splitter.split("a, , b , , ,")) {
            log.info("tmp is : [{}]", tmp);
        }

        log.info("match is : [{}]", charMatcher.retainFrom("1j2j3j4"));
    }

    @Test
    public void Test2() {
        List<Integer> list = Ints.asList(1,3,5,7,9);
        log.info("joiner is : [{}]", Ints.join(",",1,2,3,4,5));

        int[] newIntArray = Ints.concat(new int[]{1,2}, new int[]{2,3,4});
        log.info("length is : [{}]", newIntArray.length);

        log.info("max is : [{}]  min is : [{}]", Ints.max(newIntArray), Ints.min(newIntArray));

        log.info(" is contain: [{}]", Ints.contains(newIntArray, 6));

        int[] someArray = Ints.toArray(list);
    }

    @Test
    public void Test3() {
        Multiset<String> multiset = HashMultiset.create();

        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");

        log.info(" size is : [{}]",multiset.size());
        log.info(" a num is : [{}]",multiset.count("a"));
    }
    
    @Test
    public void Test4() {
        List<String> immutable = ImmutableList.of("a","b","c");

        List<String> immutable2 = ImmutableList.copyOf(immutable);
        immutable.add("d");
        immutable2.add("d");
        log.info(">>> immutable: {}",immutable);
        log.info(">>> immutable2: {}",immutable2);
    }

    /**
     * 友情提示下，guava所有的集合都有create方法，这样的好处在于简单，而且我们不必在重复泛型信息了。
     * get()/keys()/keySet()/values()/entries()/asMap()都是非常有用的返回view collection的方法。
     *
     * Multimap的实现类有：ArrayListMultimap/HashMultimap/LinkedHashMultimap/TreeMultimap/ImmutableMultimap/......
     */
    @Test
    public void Test5() {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("name","w");
        multimap.put("name","r");
        multimap.put("name","h");
        multimap.put("male","yes");

        log.info(" get is : {}  type: {}",multimap.get("name"), multimap.get("name").getClass());
        log.info(" contain : {}", multimap.containsValue("w"));

    }

    /**
     * 可不可以双向：BiMap
     * JDK提供的MAP让我们可以find value by key，那么能不能通过find key by value呢，能不能KEY和VALUE都是唯一的呢。
     * 这是一个双向的概念，即forward+backward。在实际场景中有这样的需求吗？比如通过用户ID找到mail，也需要通过mail找回用户名。
     * 没有guava的时候，我们需要create forward map AND create backward map，and now just let guava do that for you.
     *
     * biMap / biMap.inverse() / biMap.inverse().inverse() 它们是什么关系呢？你可以稍微看一下BiMap的源码实现，
     * 实际上，当你创建BiMap的时候，在内部维护了2个map，一个forward map，一个backward map，并且设置了它们之间的关系。
     * 因此，biMap.inverse() != biMap ；biMap.inverse().inverse() == biMap
     * 可不可以多个KEY：Table
     */
    @Test
    public void Test6() {
        BiMap<String, String> biMap = HashBiMap.create();

        biMap.put("name", "wrh");
        biMap.forcePut("n", "wrh");

        log.info("根据value找key： {}", biMap.inverse().get("wrh"));
    }

    /**
     * 可不可以多个KEY：Table
     * 我们知道数据库除了主键外，还提供了复合索引，而且实际中这样的多级关系查找也是比较多的，
     * 当然我们可以利用嵌套的Map来实现：Map<k1,Map<k2,v2>>。为了让我们的代码看起来不那么丑陋，guava为我们提供了Table。
     *
     * Table
     * Table涉及到3个概念：rowKey,columnKey,value，并提供了多种视图以及操作方法让你更加轻松的处理多个KEY的场景。
     */
    @Test
    public void Test7() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("张三","计算机", 100);
        table.put("张三","数学", 98);
        table.put("张三","语文", 95);
        table.put("李四","计算机", 70);
        table.put("李四","数学", 60);
        table.put("李四","语文", 55);

        Set<Table.Cell<String, String, Integer>> set = table.cellSet();
        for(Table.Cell cell : set) {
            log.info(" rowkey: {}   columnKey: {}, value: {}", cell.getRowKey(), cell.getColumnKey(), cell.getValue());
        }

        Set<String> rowSet = table.rowKeySet();
        log.info(">>> rowSet: {}", rowSet);

        Set columnSet = table.columnKeySet();
        log.info(">>> columnSet : {}", columnSet);

        Map<String, Integer> map = table.row("张三");
        log.info(">>> map: {}", map);

        Map<String, Integer> map2 = table.column("计算机");
        log.info(">>> map2: {}", map2);
    }
    
    @Test
    public void Test8() {
        String name="";
//        name = null;
        Preconditions.checkNotNull(name, "name must be given");

        Integer age = 15;
        Preconditions.checkArgument(age>18, "age must be greater 18");
    }

    private static final CacheLoader<Long, User> userCacheLoader = new CacheLoader<Long, User>() {
        @Override
        public User load(Long aLong) throws Exception {
            User user = new User("001", Thread.currentThread().getName() +"-"+
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "-" + aLong);
            log.info("load: {}",user.toString());

            return user;
        }
    };

    private static final LoadingCache<Long, User> userCacheData = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS)
            .expireAfterWrite(2,TimeUnit.SECONDS)
            .refreshAfterWrite(3,TimeUnit.SECONDS)
            .maximumSize(10000L)
            .build(userCacheLoader);

    @Test
    public void Test9() throws ExecutionException {
        log.info(">>> {}",userCacheData.get(1L));
        log.info(">>> {}",userCacheData.get(1L));
        log.info(">>> {}",userCacheData.get(1L));

    }

    /**
     * JDK中提供了Future/FutureTask/Callable来对异步回调进行支持，但是还是看上去挺复杂的，能不能更加简单呢？比如注册一个监听回调。
     */
    @Test
    public void Test10() {
        ExecutorService es = Executors.newFixedThreadPool(3);

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(es);

        ListenableFuture listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if(new Random().nextInt(3) == 2) {
                    throw new NullPointerException();
                }
                return 1;
            }
        });

        FutureCallback futureCallback = new FutureCallback<Integer>() {
            @Override
            public void onSuccess(final Integer o) {
                log.info(">>> success: {}", o);
            }

            @Override
            public void onFailure(final Throwable throwable) {
                log.info(">>> failure: {}", throwable.getMessage());
            }
        };

        Futures.addCallback(listenableFuture, futureCallback);
    }
}
