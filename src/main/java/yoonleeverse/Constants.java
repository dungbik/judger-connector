package yoonleeverse;

import lombok.Getter;

public class Constants {
    public static final String JUDGER_PATH = "/usr/lib/judger/libjudger.so";

    public enum ResultCode {
        WRONG_ANSWER(-1),
        SUCCESS(0),
        CPU_TIME_LIMIT_EXCEEDED(1),
        REAL_TIME_LIMIT_EXCEEDED(2),
        MEMORY_LIMIT_EXCEEDED(3),
        RUNTIME_ERROR(4),
        SYSTEM_ERROR(5),
        INIT_EVN_ERROR(6),
        COMPILE_ERROR(7),
        JUDGE_ERROR(8),
        UNK_ERROR(100)
        ;

        @Getter
        private int value;

        ResultCode(int value) {
            this.value = value;
        }
    }
}
