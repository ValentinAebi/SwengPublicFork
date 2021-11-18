package sweng;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class Benchmarking {

    private List<Student> generateStudentsList(){
        List<Student> students = new CSVReader("res/students.txt").read(1000);
        for (int i = 0; i < 10; ++i){
            students.addAll(students);
        }
        return List.copyOf(students);
    }

    private int[][] createMatrix(){
        int[][] matrix = new int[1000][1000];
        int coef = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                matrix[i][j] = coef;
                ++coef;
            }
        }
        return matrix;
    }

//    @Benchmark
//    public void dummyBenchmark(Blackhole bh) {
//        int addition = 1 + 1;
//        bh.consume(addition); // No dead code
//    }
//
//    @Benchmark
//    public void baselineBenchmark(Blackhole bh){
//        for (int i = 0; i < 50; i++) {
//            CSVReader reader = new CSVReader("res/students.txt");
//            bh.consume(reader.read(10));
//        }
//    }
//
//    @Benchmark
//    public void cachedVersionBenchmark(Blackhole bh){
//        for (int i = 0; i < 50; i++) {
//            CachedCSVReader reader = new CachedCSVReader(new CSVReader("res/students.txt"), new CSVReaderCache());
//            bh.consume(reader.read(10));
//        }
//    }

//    @Benchmark
//    public void sequentialProcessingBenchmark(Blackhole bh){
//        bh.consume(generateStudentsList().stream().map(Student::getEmail).collect(Collectors.toList()));
//    }
//
//    @Benchmark
//    public void parallelProcessingBenchmark(Blackhole bh){
//        bh.consume(generateStudentsList().parallelStream().map(Student::getEmail).collect(Collectors.toList()));
//    }
//
//    @Benchmark
//    public void linkedListContainsBenchmark(Blackhole bh){
//        Student refStudent = new Student("foo", "bar", "foo.bar@epfl.ch", "EPFL");
//        List<Student> studentsLL = new LinkedList<>(generateStudentsList());
//        for (int i = 0; i < 1000; i++) {
//            bh.consume(studentsLL.contains(refStudent));
//        }
//    }
//
//    @Benchmark
//    public void hashSetContainsBenchmark(Blackhole bh){
//        Student refStudent = new Student("foo", "bar", "foo.bar@epfl.ch", "EPFL");
//        Set<Student> studentsHashSet = new HashSet<>(generateStudentsList());
//        for (int i = 0; i < 1000; i++) {
//            bh.consume(studentsHashSet.contains(refStudent));
//        }
//    }
//
//    @Benchmark
//    public void arrayListBenchmark(Blackhole bh){
//        Student refStudent = new Student("foo", "bar", "foo.bar@epfl.ch", "EPFL");
//        List<Student> studentsArrayList = new ArrayList<>(generateStudentsList());
//        for (int i = 0; i < 10000; i++) {
//            bh.consume(studentsArrayList.get(i));
//        }
//    }
//
//    @Benchmark
//    public void linkedListBenchmark(Blackhole bh){
//        Student refStudent = new Student("foo", "bar", "foo.bar@epfl.ch", "EPFL");
//        List<Student> studentsArrayList = new LinkedList<>(generateStudentsList());
//        for (int i = 0; i < 10000; i++) {
//            bh.consume(studentsArrayList.get(i));
//        }
//    }
//
//    @Benchmark
//    public void iteratorBenchmark(Blackhole bh){
//        Student refStudent = new Student("foo", "bar", "foo.bar@epfl.ch", "EPFL");
//        List<Student> studentsIterator = new LinkedList<>(generateStudentsList());
//        for (Student student : studentsIterator) {
//            bh.consume(student);
//        }
//    }

    @Benchmark
    public void rowMajorBenchmark(Blackhole bh){
        int[][] matrix = createMatrix();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                sum += matrix[i][j];
            }
        }
        bh.consume(sum);
    }

    @Benchmark
    public void colMajorBenchmark(Blackhole bh){
        int[][] matrix = createMatrix();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                sum += matrix[j][i];
            }
        }
        bh.consume(sum);
    }


}
