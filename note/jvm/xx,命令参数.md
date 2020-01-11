# 命令参数



| 名称                       | 含义                                                      | 备注                         |
| -------------------------- | --------------------------------------------------------- | ---------------------------- |
| -verbose:gc                | 在控制台输出GC情况                                        |                              |
| -XX:+PrintGCDetails        | 查看GC日志<br />在控制台输出详细的GC情况                  |                              |
| -Xloggc: filepath          | 将GC日志输出到指定文件                                    |                              |
| -XX:+UseConcMarkSweepGC    | 使用老年代收集器CMS                                       | 新生代会使用ParNew           |
| -XX:+UseParNewGC           | 选择使用 ParNew                                           |                              |
| -XX:ParallelGCThreads      | 限制ParNew垃圾收集的线程数                                |                              |
| -XX:MaxGCPauseMillis       | 控制最大垃圾收集停顿时间<br />大于0的ms数                 | Parallel Scavenge 收集器使用 |
| -XX:GCTimeRatio            | 设置吞吐量大小<br />大于0 小于100的整数<br />默认值99，1% | Parallel Scavenge 收集器使用 |
| -XX:+UseAdaptiveSizePolicy | 自适应调节策略开启                                        | Parallel Scavenge 收集器使用 |
|                            |                                                           |                              |
|                            |                                                           |                              |
|                            |                                                           |                              |
|                            |                                                           |                              |
|                            |                                                           |                              |