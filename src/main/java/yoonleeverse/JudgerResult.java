package yoonleeverse;

import lombok.Data;

import static yoonleeverse.Constants.ResultCode.SYSTEM_ERROR;

@Data
public class JudgerResult {
    private int cpu_time;
    private int real_time;
    private long memory;
    private int signal;
    private int exit_code;
    private int error;
    private int result;

    public static JudgerResult makeResult(Constants.ResultCode resultCode) {
        JudgerResult judgerResult = new JudgerResult();
        judgerResult.setResult(resultCode.getValue());
        return judgerResult;
    }
}