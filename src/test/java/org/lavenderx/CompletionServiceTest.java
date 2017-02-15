package org.lavenderx;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletionServiceTest {


    static class Task implements Callable<String> {
        private int i;

        Task(int i) {
            this.i = i;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(10);
            return Thread.currentThread().getName() + "执行完任务: " + i;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testExecutorCompletionService();
    }

    private static void testExecutorCompletionService() throws InterruptedException, ExecutionException {
        int threadsNumber = 8;
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < threadsNumber; i++) {
            completionService.submit(new CompletionServiceTest.Task(i));
        }

        for (int i = 0; i < threadsNumber; i++) {
            System.out.println(completionService.take().get());
        }
    }
}
