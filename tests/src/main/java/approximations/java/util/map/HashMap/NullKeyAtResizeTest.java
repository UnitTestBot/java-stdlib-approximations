package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.List;

import java.util.*;

@Test
public class NullKeyAtResizeTest {
    @Test
    public static int test_NullKeyAtResize (int execution) throws Exception {
        List<Object> old_order = new ArrayList<>();
        Map<Object,Object> m = new HashMap<>(16);
        int number = 0;
        while(number < 100000) {
            m.put(null,null); // try to put in null. This may cause resize.
            m.remove(null); // remove it.
            Integer adding = (number += 100);
            m.put(adding, null); // try to put in a number. This wont cause resize.
            List<Object> new_order = new ArrayList<>();
            new_order.addAll(m.keySet());
            new_order.remove(adding);
            if(!old_order.equals(new_order)) {
                // we resized and didn't crash.
                break;
            }
            // remember this order for the next time around.
            old_order.clear();
            old_order.addAll(m.keySet());
        }
        if(number == 100000) {
            return -1;
        }
        return execution;
    }
}
