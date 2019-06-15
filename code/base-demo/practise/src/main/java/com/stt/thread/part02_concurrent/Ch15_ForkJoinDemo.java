package com.stt.thread.part02_concurrent;

import java.util.concurrent.*;

public class Ch15_ForkJoinDemo {

    static class CountTask extends RecursiveTask<Integer>{

        private static final int THRESHOLD = 2;
        private int start,end;

        public CountTask(int start,int end){
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            boolean canCompute = (end - start) <= THRESHOLD;
            if(canCompute){
                for(int i = start;i<=end;i++){
                    sum += i;
                }
            }else{
                // 不满足阈值条件，进行拆分计算
                int middle = (start + end) / 2;
                // 生成2个子任务
                CountTask leftTask = new CountTask(start,middle);
                CountTask rightTask = new CountTask(middle + 1,end);
                // 执行子任务
                leftTask.fork();
                rightTask.fork();
                // 等待子任务执行完成，获取结果
                int leftSum = leftTask.join();
                int rightSum = rightTask.join();
                // 合并子任务
                sum = leftSum + rightSum;
            }
            if(true){
                // 测试异常使用
//                throw new RuntimeException("error");
            }

            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 定义一个线程池，默认大小是系统核心数
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1,100);
//        Future<Integer> re = forkJoinPool.submit(task);
//        System.out.println(re.get());

        forkJoinPool.execute(task);
        //关闭执行器，并配合超时。来等待任务运行完成
        forkJoinPool.shutdown();
        // 该执行器如果没有可活动的任务
        // 执行器会自动关闭。而且调用get会阻塞任务直到返回结果
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        if(task.isCompletedAbnormally()){
            // 检查任务是否已经抛出异常或已经被取消了，要注意此方法。由于提交任务之后，检测该任务是否有异常，不是阻塞的
            // 需要上面的等待任务的完成。才能正确的获取到是否有异常
            System.out.println("---");
            System.out.println(task.getException());
        }else{
            System.out.println(task.join());
        }
    }

}
