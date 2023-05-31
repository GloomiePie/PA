package org.example;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AppSimple {
    public static void main(String[] args) throws IOException{
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    //@BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 4, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(value = 2)
    public void doSomething(){
        IntStream values = IntStream.range(1, 1_000_000);
        var result = values.filter(AppSimple::isPrime);
        System.out.printf("Nro. Primos: %d\n", result.count());
    }
    public static boolean isPrime(int nro){
        if(nro < 2) return false;
        if(nro == 2) return true;
        return false;
    }
}
