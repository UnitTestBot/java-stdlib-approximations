// Generated by the LibSL translator.  DO NOT EDIT!
// source: ?
//
package generated.java.lang;

import generated.runtime.LibSLGlobals;
import java.io.Console;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.SecurityManager;
import java.lang.String;
import java.lang.Void;
import java.util.Properties;
import jdk.internal.misc.VM;
import org.jacodb.approximation.annotation.Approximate;
import org.usvm.api.Engine;
import runtime.LibSLRuntime;
import stub.runtime.utils.SymbolicInputStream;

/**
 * SystemAutomaton for LSLSystem ~> java.lang.System
 */
@Approximate(java.lang.System.class)
public final class System implements LibSLRuntime.Automaton {
    private static final LibSLRuntime.Map<String, String> propsMap = new LibSLRuntime.Map<>(new LibSLRuntime.HashMapContainer<>());

    private static volatile SecurityManager security = null;

    private static Properties props = null;

    private static Console console = null;

    public static InputStream in = null;

    public static PrintStream out = null;

    public static PrintStream err = null;

    private static final long NANOTIME_BEGINNING_OF_TIME = 1000L;

    private static final long NANOTIME_WARP_MAX = 1000L;

    static {
        /* SystemAutomaton::__clinit__() */ {
            initPhase1();
            initPhase2();
            initPhase3();
        }
    }

    @LibSLRuntime.AutomatonConstructor
    public System(Void __$lsl_token, final byte p0) {
    }

    @LibSLRuntime.AutomatonConstructor
    public System(final Void __$lsl_token) {
        this(__$lsl_token, __$lsl_States.Initialized);
    }

    /**
     * [CONSTRUCTOR] SystemAutomaton::LSLSystem(LSLSystem) -> LSLSystem
     */
    private System() {
        this((Void) null);
        /* body */ {
        }
    }

    /**
     * [SUBROUTINE] SystemAutomaton::_makeValidString(int, int) -> String
     */
    private static String _makeValidString(int minLen, int maxLen) {
        String result = null;
        /* body */ {
            final int len = Engine.makeSymbolicInt();
            Engine.assume(len >= minLen);
            Engine.assume(len < maxLen);
            final int lastCharIdx = len - 1;
            final char[] chars = new char[len];
            final String forbidenLetters = ":/\\*\"'?<>|";
            int spaces = 0;
            int i = 0;
            for (i = 0; i < len; i += 1) {
                final char c = Engine.makeSymbolicChar();
                Engine.assume(c >= 20);
                Engine.assume(forbidenLetters.indexOf(c) == -1);
                if (c == 20) {
                    spaces += 1;
                }
                chars[i] = c;
            }
            ;
            Engine.assume((spaces + 1) < maxLen);
            Engine.assume(chars[lastCharIdx] != 20);
            result = LibSLRuntime.toString(chars);
        }
        return result;
    }

    /**
     * [SUBROUTINE] SystemAutomaton::_getRandomDriveLetter() -> String
     */
    private String _getRandomDriveLetter() {
        String result = null;
        /* body */ {
            final String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
            final int idx = Engine.makeSymbolicInt();
            Engine.assume(idx >= 0);
            Engine.assume(idx < letters.length);
            result = letters[idx];
        }
        return result;
    }

    /**
     * [SUBROUTINE] SystemAutomaton::_initProperties() -> void
     */
    private static void _initProperties() {
        /* body */ {
            final int javaVersion = Engine.makeSymbolicInt();
            Engine.assume(javaVersion >= 8);
            Engine.assume(javaVersion <= 11);
            final String userName = _makeValidString(1, 25);
            propsMap.set("file.encoding", "Cp1251");
            propsMap.set("sun.io.unicode.encoding", "UnicodeLittle");
            propsMap.set("sun.jnu.encoding", "Cp1251");
            propsMap.set("sun.stderr.encoding", "cp866");
            propsMap.set("sun.stdout.encoding", "cp866");
            final String[] versionStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
            final String versionString = versionStrings[javaVersion];
            propsMap.set("java.specification.name", "Java Platform API Specification");
            propsMap.set("java.specification.vendor", "Oracle Corporation");
            propsMap.set("java.specification.version", versionString);
            propsMap.set("java.vm.info", "mixed mode");
            propsMap.set("java.vm.name", "OpenJDK 64-Bit Server VM");
            propsMap.set("java.vm.specification.name", "Java Virtual Machine Specification");
            propsMap.set("java.vm.specification.vendor", "Oracle Corporation");
            propsMap.set("java.vm.specification.version", versionString);
            propsMap.set("java.vm.vendor", "Eclipse Adoptium");
            propsMap.set("java.vm.version", versionString.concat(".0.362+9"));
            propsMap.set("java.library.path", "C:\\Program Files\\Eclipse Adoptium\\jdk-8.0.362.9-hotspot\\bin;C:\\Windows\\Sun\\Java\\bin;C:\\Windows\\system32;.");
            propsMap.set("java.home", "C:\\Program Files\\Eclipse Adoptium\\jdk-8.0.362.9-hotspot");
            propsMap.set("sun.boot.library.path", "C:\\Program Files\\Eclipse Adoptium\\jdk-8.0.362.9-hotspot\\bin");
            propsMap.set("java.io.tmpdir", "T:\\Temp\\");
            propsMap.set("java.class.path", ".");
            if (LibSLGlobals.SYSTEM_IS_WINDOWS) {
                propsMap.set("file.separator", "\\");
                propsMap.set("line.separator", "\r\n");
                propsMap.set("path.separator", ";");
            } else {
                propsMap.set("file.separator", "/");
                propsMap.set("line.separator", "\n");
                propsMap.set("path.separator", ":");
            }
            propsMap.set("user.country", "RU");
            propsMap.set("user.country.format", "US");
            propsMap.set("user.language", "ru");
            final String[] bytecodeVersions = { "?", "?", "?", "?", "?", "49.0", "50.0", "51.0", "52.0", "53.0", "54.0", "55.0", "?", "?", "?", "?" };
            propsMap.set("java.class.version", bytecodeVersions[javaVersion]);
            propsMap.set("os.arch", "amd64");
            propsMap.set("os.name", "Windows 10");
            propsMap.set("os.version", "10.0");
            propsMap.set("sun.arch.data.model", "64");
            propsMap.set("sun.cpu.endian", "little");
            propsMap.set("sun.cpu.isalist", "amd64");
            propsMap.set("sun.desktop", "windows");
            propsMap.set("user.dir", "D:\\Company\\Prod\\Service");
            propsMap.set("user.home", "C:\\Users\\".concat(userName));
            propsMap.set("user.name", userName);
            propsMap.set("user.script", "");
            propsMap.set("user.timezone", "");
            propsMap.set("user.variant", "");
            propsMap.set("sun.java.command", "org.example.MainClass");
            propsMap.set("awt.toolkit", "sun.awt.windows.WToolkit");
            propsMap.set("java.awt.graphicsenv", "sun.awt.Win32GraphicsEnvironment");
            propsMap.set("java.awt.printerjob", "sun.awt.windows.WPrinterJob");
            propsMap.set("sun.java.launcher", "SUN_STANDARD");
            propsMap.set("sun.management.compiler", "HotSpot 64-Bit Tiered Compilers");
            propsMap.set("sun.nio.MaxDirectMemorySize", "-1");
            propsMap.set("sun.os.patch.level", "");
            propsMap.set("java.vm.compressedOopsMode", "Zero based");
            propsMap.set("jdk.boot.class.path.append", "");
            propsMap.set("jdk.debug", "release");
            props = null;
        }
    }

    /**
     * [SUBROUTINE] SystemAutomaton::initPhase1() -> void
     */
    private static void initPhase1() {
        /* body */ {
            _initProperties();
            final InputStream newInput = (SymbolicInputStream) ((Object) new generated.runtime.utils.SymbolicInputStream((Void) null, 
                /* state = */ generated.runtime.utils.SymbolicInputStream.__$lsl_States.Initialized, 
                /* maxSize = */ 1000, 
                /* supportMarks = */ false, 
                /* dataSize = */ -1, 
                /* data = */ null, 
                /* closed = */ false, 
                /* pos = */ 0, 
                /* markPos = */ -1, 
                /* markLimit = */ 0
            ));
            in = newInput;
            out = (stub.java.lang.System_PrintStream) ((Object) new System_PrintStream((Void) null, 
                /* state = */ System_PrintStream.__$lsl_States.Initialized, 
                /* closed = */ false, 
                /* error = */ false
            ));
            err = (stub.java.lang.System_PrintStream) ((Object) new System_PrintStream((Void) null, 
                /* state = */ System_PrintStream.__$lsl_States.Initialized, 
                /* closed = */ false, 
                /* error = */ false
            ));
            VM.initLevel(1);
        }
    }

    /**
     * [SUBROUTINE] SystemAutomaton::initPhase2() -> int
     */
    private static int initPhase2() {
        int result = 0;
        /* body */ {
            VM.initLevel(2);
            result = 0;
        }
        return result;
    }

    /**
     * [SUBROUTINE] SystemAutomaton::initPhase3() -> void
     */
    private static void initPhase3() {
        /* body */ {
            security = null;
            VM.initLevel(3);
            VM.initLevel(4);
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::clearProperty(String) -> String
     */
    public static String clearProperty(String key) {
        String result = null;
        /* body */ {
            if (key == null) {
                throw new NullPointerException("key can't be null");
            }
            if (key.length() == 0) {
                throw new NullPointerException("key can't be empty");
            }
            if (propsMap.hasKey(key)) {
                result = propsMap.get(key);
                propsMap.remove(key);
            }
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::console() -> Console
     */
    public static Console console() {
        Console result = null;
        /* body */ {
            result = console;
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::exit(int) -> void
     */
    public static void exit(int status) {
        /* body */ {
            LibSLRuntime.error("Unexpected shutdown");
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::gc() -> void
     */
    public static void gc() {
        /* body */ {
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::getProperties() -> Properties
     */
    public static Properties getProperties() {
        Properties result = null;
        /* body */ {
            result = props;
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::getProperty(String) -> String
     */
    public static String getProperty(String key) {
        String result = null;
        /* body */ {
            if (key == null) {
                throw new NullPointerException("key can't be null");
            }
            if (key.length() == 0) {
                throw new NullPointerException("key can't be empty");
            }
            if (propsMap.hasKey(key)) {
                result = propsMap.get(key);
            } else {
                result = null;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::getProperty(String, String) -> String
     */
    public static String getProperty(String key, String def) {
        String result = null;
        /* body */ {
            if (key == null) {
                throw new NullPointerException("key can't be null");
            }
            if (key.length() == 0) {
                throw new NullPointerException("key can't be empty");
            }
            if (propsMap.hasKey(key)) {
                result = propsMap.get(key);
            } else {
                result = def;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::getenv(String) -> String
     */
    public static String getenv(String name) {
        String result = null;
        /* body */ {
            final int symbolCount = Engine.makeSymbolicInt();
            Engine.assume(symbolCount >= 0);
            Engine.assume(symbolCount < 256);
            final char[] symbols = Engine.makeSymbolicCharArray(symbolCount);
            Engine.assume(symbols != null);
            result = LibSLRuntime.toString(symbols);
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::identityHashCode(Object) -> int
     */
    public static int identityHashCode(Object x) {
        int result = 0;
        /* body */ {
            result = Engine.makeSymbolicInt();
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::lineSeparator() -> String
     */
    public static String lineSeparator() {
        String result = null;
        /* body */ {
            result = propsMap.get("line.separator");
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::mapLibraryName(String) -> String
     */
    public static String mapLibraryName(String libname) {
        String result = null;
        /* body */ {
            if (libname == null) {
                throw new NullPointerException();
            }
            final int len = libname.length();
            if (len > 240) {
                throw new IllegalArgumentException("name too long");
            }
            if (LibSLGlobals.SYSTEM_IS_WINDOWS) {
                result = libname.concat(".dll");
            } else {
                if (LibSLGlobals.SYSTEM_IS_MAC) {
                    result = "lib".concat(libname).concat(".dylib");
                } else {
                    result = "lib".concat(libname).concat(".so");
                }
            }
        }
        return result;
    }

    /**
     * [FUNCTION] SystemAutomaton::runFinalization() -> void
     */
    public static void runFinalization() {
        /* body */ {
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::setErr(PrintStream) -> void
     */
    public static void setErr(PrintStream stream) {
        /* body */ {
            if (stream == null) {
                throw new NullPointerException();
            }
            err = stream;
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::setIn(InputStream) -> void
     */
    public static void setIn(InputStream stream) {
        /* body */ {
            if (stream == null) {
                throw new NullPointerException();
            }
            in = stream;
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::setOut(PrintStream) -> void
     */
    public static void setOut(PrintStream stream) {
        /* body */ {
            if (stream == null) {
                throw new NullPointerException();
            }
            out = stream;
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::setProperties(Properties) -> void
     */
    public static void setProperties(Properties p) {
        /* body */ {
            props = p;
        }
    }

    /**
     * [FUNCTION] SystemAutomaton::setProperty(String, String) -> String
     */
    public static String setProperty(String key, String value) {
        String result = null;
        /* body */ {
            if (key == null) {
                throw new NullPointerException("key can't be null");
            }
            if (key.length() == 0) {
                throw new NullPointerException("key can't be empty");
            }
            if (propsMap.hasKey(key)) {
                result = propsMap.get(key);
            } else {
                result = null;
            }
            propsMap.set(key, value);
        }
        return result;
    }

    public static final class __$lsl_States {
        public static final byte Initialized = (byte) 0;
    }

    @Approximate(System.class)
    public static final class __hook {
        private __hook(Void o1, Void o2) {
            Engine.assume(false);
        }
    }
}
