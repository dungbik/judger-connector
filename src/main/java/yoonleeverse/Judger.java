package yoonleeverse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static yoonleeverse.Constants.ResultCode.*;

public class Judger {
    public JudgerResult compile(String folder, String[] command) {
        synchronized (this) {
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File(folder));
            pb.command(command);

            try {
                Process process = pb.start();
                process.waitFor(10, TimeUnit.SECONDS);

                return JudgerResult.makeResult(process.exitValue() == 0 ? SUCCESS : COMPILE_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return JudgerResult.makeResult(SUCCESS);
        }
    }

    public JudgerResult judge(String folder, JudgerParam param) {
        final String[] commands = makeCommand(param);

        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(folder));
        pb.command(commands);

        try {
            Process process = pb.start();
            String result = new String(process.getInputStream().readAllBytes());
            JudgerResult judgerResult = new ObjectMapper().readValue(result, JudgerResult.class);
            return judgerResult;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JudgerResult.makeResult(JUDGE_ERROR);
    }

    public String[] makeCommand(JudgerParam param) {
        List<String> command = new ArrayList<>();
        command.add(Constants.JUDGER_PATH);

        Class vo = param.getClass();
        for (Field field : vo.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(param);
                if (value == null)
                    continue;
                if (field.getType() == List.class) {
                    for (String str : (List<String>) value) {
                        command.add(String.format("--%s=%s", field.getName(), str));
                    }
                } else {
                    command.add(String.format("--%s=%s", field.getName(), value));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return command.toArray(new String[0]);
    }
}
