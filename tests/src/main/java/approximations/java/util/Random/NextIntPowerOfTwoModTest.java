package approximations.java.util.Random;

import approximations.Test;

import java.util.Random;

@Test
public class NextIntPowerOfTwoModTest {
    @Test
    public static int test_NextIntPowerOfTwoMod (int execution) {
        Random r = new Random(69);
        int total = 0;
        for (int i=0; i<1000; i++)
            total += r.nextInt(16);
        if (total != 7639)
            return -1;
        return execution;
    }
}
