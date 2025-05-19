package encoders.java.util;

import generated.java.util.map.ConcurrentReferenceHashMapImpl;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.usvm.api.encoder.EncoderFor;
import org.usvm.api.encoder.ObjectEncoder;

@EncoderFor(ConcurrentReferenceHashMap.class)
public class ConcurrentReferenceHashMap_Encoder implements ObjectEncoder {

    @SuppressWarnings("unchecked")
    @Override
    public Object encode(Object object) {
        ConcurrentReferenceHashMap<Object, Object> map = (ConcurrentReferenceHashMap<Object, Object>) object;
        return new ConcurrentReferenceHashMapImpl<>(map);
    }
}
