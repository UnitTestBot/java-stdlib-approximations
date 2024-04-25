// Generated by the LibSL translator.  DO NOT EDIT!
// sources:
//  - java/util/Spliterators.lsl:62
//  - java/util/Spliterators.IntArraySpliterator.lsl:21
//
package generated.java.util;

import generated.runtime.LibSLGlobals;
import java.lang.IllegalStateException;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import org.jacodb.approximation.annotation.Approximate;
import org.usvm.api.Engine;
import runtime.LibSLRuntime;

/**
 * Spliterators_IntArraySpliteratorAutomaton for Spliterators_IntArraySpliterator ~> java.util.Spliterators_IntArraySpliterator
 */
@SuppressWarnings({"all", "unchecked"})
@Approximate(stub.java.util.Spliterators_IntArraySpliterator.class)
public final class Spliterators_IntArraySpliterator implements LibSLRuntime.Automaton, Spliterator.OfInt {
    static {
        Engine.assume(true);
    }

    private byte __$lsl_state = __$lsl_States.Allocated;

    public int[] array;

    public int index;

    public int fence;

    public int characteristics;

    @LibSLRuntime.AutomatonConstructor
    public Spliterators_IntArraySpliterator(Void __$lsl_token, final byte p0, final int[] p1,
            final int p2, final int p3, final int p4) {
        this.__$lsl_state = p0;
        this.array = p1;
        this.index = p2;
        this.fence = p3;
        this.characteristics = p4;
    }

    @LibSLRuntime.AutomatonConstructor
    public Spliterators_IntArraySpliterator(final Void __$lsl_token) {
        this(__$lsl_token, __$lsl_States.Allocated, null, 0, 0, 0);
    }

    /**
     * [CONSTRUCTOR] Spliterators_IntArraySpliteratorAutomaton::<init>(Spliterators_IntArraySpliterator, array<int>, int) -> void
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:81
     */
    public Spliterators_IntArraySpliterator(int[] arr, int additionalCharacteristics) {
        this((Void) null);
        Engine.assume(this.__$lsl_state == __$lsl_States.Allocated);
        /* body */ {
            this.array = arr;
            this.index = 0;
            this.fence = arr.length;
            this.characteristics = additionalCharacteristics | LibSLGlobals.SPLITERATOR_SIZED | LibSLGlobals.SPLITERATOR_SUBSIZED;
        }
        this.__$lsl_state = __$lsl_States.Initialized;
    }

    /**
     * [CONSTRUCTOR] Spliterators_IntArraySpliteratorAutomaton::<init>(Spliterators_IntArraySpliterator, array<int>, int, int, int) -> void
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:93
     */
    public Spliterators_IntArraySpliterator(int[] arr, int origin, int pFence,
            int additionalCharacteristics) {
        this((Void) null);
        Engine.assume(this.__$lsl_state == __$lsl_States.Allocated);
        /* body */ {
            this.array = arr;
            this.index = origin;
            this.fence = pFence;
            this.characteristics = additionalCharacteristics | LibSLGlobals.SPLITERATOR_SIZED | LibSLGlobals.SPLITERATOR_SUBSIZED;
        }
        this.__$lsl_state = __$lsl_States.Initialized;
    }

    /**
     * [SUBROUTINE] Spliterators_IntArraySpliteratorAutomaton::_hasCharacteristics(int) -> boolean
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:73
     */
    private boolean _hasCharacteristics(int _characteristics) {
        boolean result = false;
        /* body */ {
            result = (this.characteristics & _characteristics) == _characteristics;
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::characteristics(Spliterators_IntArraySpliterator) -> int
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:109
     */
    public int characteristics() {
        int result = 0;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            result = this.characteristics;
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::estimateSize(Spliterators_IntArraySpliterator) -> long
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:115
     */
    public long estimateSize() {
        long result = 0L;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            result = ((long) (this.fence - this.index));
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::forEachRemaining(Spliterators_IntArraySpliterator, Consumer) -> void
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:145
     */
    public void forEachRemaining(Consumer _action) {
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            if (_action == null) {
                throw new NullPointerException();
            }
            final int[] a = this.array;
            int hi = this.fence;
            int i = this.index;
            this.index = hi;
            for (i = i; i < hi; i += 1) {
                final int item = a[i];
                _action.accept(item);
            }
            ;
        }
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::forEachRemaining(Spliterators_IntArraySpliterator, IntConsumer) -> void
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:151
     */
    public void forEachRemaining(IntConsumer _action) {
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            if (_action == null) {
                throw new NullPointerException();
            }
            final int[] a = this.array;
            int hi = this.fence;
            int i = this.index;
            this.index = hi;
            for (i = i; i < hi; i += 1) {
                final int item = a[i];
                _action.accept(item);
            }
            ;
        }
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::getComparator(Spliterators_IntArraySpliterator) -> Comparator
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:166
     */
    public Comparator getComparator() {
        Comparator result = null;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            if (_hasCharacteristics(LibSLGlobals.SPLITERATOR_SORTED)) {
                result = null;
            } else {
                throw new IllegalStateException();
            }
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::getExactSizeIfKnown(Spliterators_IntArraySpliterator) -> long
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:175
     */
    public long getExactSizeIfKnown() {
        long result = 0L;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            result = ((long) (this.fence - this.index));
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::hasCharacteristics(Spliterators_IntArraySpliterator, int) -> boolean
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:181
     */
    public boolean hasCharacteristics(int _characteristics) {
        boolean result = false;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            result = _hasCharacteristics(_characteristics);
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::tryAdvance(Spliterators_IntArraySpliterator, IntConsumer) -> boolean
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:211
     */
    public boolean tryAdvance(IntConsumer _action) {
        boolean result = false;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            if (_action == null) {
                throw new NullPointerException();
            }
            final int hi = this.fence;
            final int i = this.index;
            if (i < hi) {
                this.index = i + 1;
                final int item = array[i];
                _action.accept(item);
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::tryAdvance(Spliterators_IntArraySpliterator, Consumer) -> boolean
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:217
     */
    public boolean tryAdvance(Consumer _action) {
        boolean result = false;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            if (_action == null) {
                throw new NullPointerException();
            }
            final int hi = this.fence;
            final int i = this.index;
            if (i < hi) {
                this.index = i + 1;
                final int item = array[i];
                _action.accept(item);
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] Spliterators_IntArraySpliteratorAutomaton::trySplit(Spliterators_IntArraySpliterator) -> Spliterator_OfInt
     * Source: java/util/Spliterators.IntArraySpliterator.lsl:232
     */
    public Spliterator.OfInt trySplit() {
        Spliterator.OfInt result = null;
        Engine.assume(this.__$lsl_state == __$lsl_States.Initialized);
        /* body */ {
            final int hi = this.fence;
            final int lo = this.index;
            final int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                result = null;
            } else {
                result = (stub.java.util.Spliterators_IntArraySpliterator) ((Object) new Spliterators_IntArraySpliterator((Void) null, 
                    /* state = */ Spliterators_IntArraySpliterator.__$lsl_States.Initialized, 
                    /* array = */ this.array, 
                    /* index = */ lo, 
                    /* fence = */ mid, 
                    /* characteristics = */ this.characteristics
                ));
            }
            this.index = mid;
        }
        return result;
    }

    public static final class __$lsl_States {
        public static final byte Allocated = (byte) 0;

        public static final byte Initialized = (byte) 1;
    }

    @Approximate(Spliterators_IntArraySpliterator.class)
    public static final class __hook {
        private __hook(Void o1, Void o2) {
            Engine.assume(false);
        }
    }
}
