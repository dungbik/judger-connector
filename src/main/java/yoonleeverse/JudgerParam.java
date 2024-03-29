package yoonleeverse;

import lombok.Builder;

import java.util.List;

@Builder
public class JudgerParam {
    private Integer max_cpu_time;
    private Integer max_real_time;
    private Integer max_memory;
    private Integer memory_limit_check_only;
    private Integer max_stack;
    private Integer max_process_number;
    private Integer max_output_size;

    private String exe_path;
    private List<String> input_path;
    private String output_path;
    private String error_path;

    private List<String> args;
    private List<String> env;

    private String log_path;
    private String seccomp_rule_name;

    private Integer uid;
    private Integer gid;
}
