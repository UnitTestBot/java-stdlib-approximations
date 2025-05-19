package encoders.java.util;

import generated.java.util.map.AbstractMap_EntryImpl;
import org.usvm.api.encoder.EncoderFor;
import org.usvm.api.encoder.ObjectEncoder;
import stub.java.util.map.AbstractMap_Entry;

import java.util.Map;

@EncoderFor(AbstractMap_Entry.class)
public class AbstractMap_Entry_Encoder implements ObjectEncoder {

    @Override
    public Object encode(Object entry) {
        if (entry instanceof AbstractMap_Entry)
            return new AbstractMap_EntryImpl<>(null, null);

        return new AbstractMap_EntryImpl<>((Map.Entry<?, ?>) entry);
    }
}
