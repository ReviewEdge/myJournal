package myJournal;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

public class BenchmarkHashing {

    public static void main(String[] args) {
        byte[] pass = new byte[12];
        Random r = new Random();
        for(int i = 1; i < 16; i++) {
            r.nextBytes(pass);
            long then = System.nanoTime();
            String e = BCrypt.gensalt(i);
            long now = System.nanoTime();
            long elapsed = now - then;
            System.out.println(i + "rounds took" + (elapsed / 1000000.0) + "ms");
        }
    }

}
