# java8默认的使用的垃圾回收器

通过以下命令可以查看java使用的GC

```bash
java -XX:+PrintCommandLineFlags -version
```

java默认使用的是parallelGC

* 新生代

新生代 默认使用 parallel scavenge垃圾收集器

parallel scavenge 底层默认使用的 标记-复制算法

* 老年代

老年代 默认使用parallel old 垃圾收集器

parallel old 底层默认使用的使用 标记-整理算法。