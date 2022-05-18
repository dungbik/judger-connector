package yooneeverse;

import com.sun.jna.Structure;

import java.util.List;

public class Result extends Structure {

    public static final List<String> FIELDS =
            createFieldsOrder("cpu_time", "real_time", "memory", "signal", "exit_code", "error", "result");

    public int cpu_time;
    public int real_time;
    public long memory;
    public int signal;
    public int exit_code;
    public int error;
    public int result;

    @Override
    protected List<String> getFieldOrder() {
        return FIELDS;
    }

    public static class ByValue extends Result implements Structure.ByValue {}
}