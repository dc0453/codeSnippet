import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dc0453 on 2016/12/19.
 */
public class TwoThread implements Runnable{
    private int count = 0;
    private Lock lock = new ReentrantLock();
    Condition c = lock.newCondition();

    @Override
    public void run() {
        while (count < 100) {
            String threadName = Thread.currentThread().getName();
            lock.lock();
            try {
                if ("thread-1".equals(threadName)) {
                    if (count % 2 == 0) {
                        System.out.println("current thread: " + Thread.currentThread().getName() + " |count: " + ++count);
                        c.signal();
                    } else {
                        c.await();
                    }
                } else {
                    if (count % 2 == 1) {
                        System.out.println("current thread: " + Thread.currentThread().getName() + " |count: " + ++count);
                        c.signal();
                    } else {
                        c.await();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

    }
    /*
    private int count = 0;
    private Object lock = new Object();

    @Override
    public void run() {
        while (count < 100) {
            String threadName = Thread.currentThread().getName();
            synchronized (lock) {
                if ("thread-1".equals(threadName)) {
                    if (count % 2 == 0) { //odd
                        System.out.println("current thread: " + Thread.currentThread().getName() + " |count: " + ++count);
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (count % 2 == 1) { //even
                        System.out.println("current thread: " + Thread.currentThread().getName() + " |count: " + ++count);
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    */
    public static void main(String[] args) {
        TwoThread t = new TwoThread();

        new Thread(t, "thread-1").start();
        new Thread(t, "thread-2").start();
    }
/* output
current thread: thread-1 |count: 1
current thread: thread-2 |count: 2
current thread: thread-1 |count: 3
current thread: thread-2 |count: 4
current thread: thread-1 |count: 5
current thread: thread-2 |count: 6
current thread: thread-1 |count: 7
current thread: thread-2 |count: 8
current thread: thread-1 |count: 9
current thread: thread-2 |count: 10
current thread: thread-1 |count: 11
current thread: thread-2 |count: 12
current thread: thread-1 |count: 13
current thread: thread-2 |count: 14
current thread: thread-1 |count: 15
current thread: thread-2 |count: 16
current thread: thread-1 |count: 17
current thread: thread-2 |count: 18
current thread: thread-1 |count: 19
current thread: thread-2 |count: 20
current thread: thread-1 |count: 21
current thread: thread-2 |count: 22
current thread: thread-1 |count: 23
current thread: thread-2 |count: 24
current thread: thread-1 |count: 25
current thread: thread-2 |count: 26
current thread: thread-1 |count: 27
current thread: thread-2 |count: 28
current thread: thread-1 |count: 29
current thread: thread-2 |count: 30
current thread: thread-1 |count: 31
current thread: thread-2 |count: 32
current thread: thread-1 |count: 33
current thread: thread-2 |count: 34
current thread: thread-1 |count: 35
current thread: thread-2 |count: 36
current thread: thread-1 |count: 37
current thread: thread-2 |count: 38
current thread: thread-1 |count: 39
current thread: thread-2 |count: 40
current thread: thread-1 |count: 41
current thread: thread-2 |count: 42
current thread: thread-1 |count: 43
current thread: thread-2 |count: 44
current thread: thread-1 |count: 45
current thread: thread-2 |count: 46
current thread: thread-1 |count: 47
current thread: thread-2 |count: 48
current thread: thread-1 |count: 49
current thread: thread-2 |count: 50
current thread: thread-1 |count: 51
current thread: thread-2 |count: 52
current thread: thread-1 |count: 53
current thread: thread-2 |count: 54
current thread: thread-1 |count: 55
current thread: thread-2 |count: 56
current thread: thread-1 |count: 57
current thread: thread-2 |count: 58
current thread: thread-1 |count: 59
current thread: thread-2 |count: 60
current thread: thread-1 |count: 61
current thread: thread-2 |count: 62
current thread: thread-1 |count: 63
current thread: thread-2 |count: 64
current thread: thread-1 |count: 65
current thread: thread-2 |count: 66
current thread: thread-1 |count: 67
current thread: thread-2 |count: 68
current thread: thread-1 |count: 69
current thread: thread-2 |count: 70
current thread: thread-1 |count: 71
current thread: thread-2 |count: 72
current thread: thread-1 |count: 73
current thread: thread-2 |count: 74
current thread: thread-1 |count: 75
current thread: thread-2 |count: 76
current thread: thread-1 |count: 77
current thread: thread-2 |count: 78
current thread: thread-1 |count: 79
current thread: thread-2 |count: 80
current thread: thread-1 |count: 81
current thread: thread-2 |count: 82
current thread: thread-1 |count: 83
current thread: thread-2 |count: 84
current thread: thread-1 |count: 85
current thread: thread-2 |count: 86
current thread: thread-1 |count: 87
current thread: thread-2 |count: 88
current thread: thread-1 |count: 89
current thread: thread-2 |count: 90
current thread: thread-1 |count: 91
current thread: thread-2 |count: 92
current thread: thread-1 |count: 93
current thread: thread-2 |count: 94
current thread: thread-1 |count: 95
current thread: thread-2 |count: 96
current thread: thread-1 |count: 97
current thread: thread-2 |count: 98
current thread: thread-1 |count: 99
current thread: thread-2 |count: 100
 */

}
