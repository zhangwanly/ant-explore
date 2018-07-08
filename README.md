## 模块说明
com.ace.explore.ant.lock：题目一，自旋锁
com.ace.explore.ant.statistics：题目二，java代码、注释统计
com.ace.explore.ant.collection：题目三，queue&stack
com.ace.explore.ant.streaming：选做题，入口com.ace.explore.ant.streaming.Main.main，需先执行com.ace.explore.ant.streaming.ResourceGenTest.genResource生成测试资源文件。

assets：资源文件目录，Override.java，Runnable.java用与java代码统计使用测试资源文件，选取代码行数稳定的jdk源码。

assets/rs：指标统计使用测试资源


## Q&A
Q:java互斥锁和自旋锁有什么差别?

A:两者都是对共享资源进行同步加锁，互斥锁未获取到锁的线程会阻塞等待，线程会处于WATTING(wait,join,park操作)、TIMED_WATTING(wait(time),join(time),park(time)操作)、BLOCKED(synchronized)状态,
直到锁被释放，被通知可继续抢占锁，其表现是悲观锁形态，对共享资源悲观认为其他线程已被占用，获取不到则“消极”等待。而自旋锁锁线程是依然处于RANNABLE状态(运行中或等待被CPU调度)，乐观的认为不会与其他线程冲突，
如果冲突了则“乐观”地不断尝试，谓之“自旋”，其核心点为：“制造冲突”(版本差异)、“发现冲突”(版本比对)、“解决冲突”(纠偏版本差异)。
互斥锁适用于操作时间较长的场景，线程上下文切换状态转换带来的损耗比较长时间空转可带来更好效率。而自旋锁适用于冲突率较小(读多写少)，操作时间很短(本地内存小计算量)操作的场景，空转尝试会比线程切换状态转移带来更高效率。

