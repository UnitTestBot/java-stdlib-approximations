package decoders;

import org.jacodb.api.jvm.JcClassOrInterface;
import org.jacodb.api.jvm.JcField;

import java.util.ArrayList;
import java.util.List;

public class Helpers {
    public static List<JcField> getAllFields(JcClassOrInterface type) {
        List<JcClassOrInterface> typesToCheck = new ArrayList<>();
        typesToCheck.add(type);
        List<JcField> allFields = new ArrayList<>();
        while (!typesToCheck.isEmpty()) {
            int lastIndex = typesToCheck.size() - 1;
            JcClassOrInterface current = typesToCheck.remove(lastIndex);
            allFields.addAll(current.getDeclaredFields());

            JcClassOrInterface superClass = current.getSuperClass();
            if (superClass != null) {
                typesToCheck.add(superClass);
            }
            typesToCheck.addAll(current.getInterfaces());
            JcClassOrInterface outerClass = current.getOuterClass();
            if (outerClass != null) {
                typesToCheck.add(outerClass);
            }
        }
        return allFields;
    }
}
