package yoonleeverse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Judger {

    public Result.ByValue judge(JudgerParam param) {
        Objects.requireNonNull(param);

        final String[] argv = getArgv(param);

        return JudgerLib.INSTANCE.judge(argv.length, argv);
    }

    public String[] getArgv(JudgerParam param) {
        List<String> argvList = new ArrayList<>();
        argvList.add(Constants.JUDGER_PATH);

        Class vo = param.getClass();
        for (Field field : vo.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(param);
                if (value == null)
                    continue;
                if (field.getType() == List.class) {
                    for (String str : (List<String>) value) {
                        argvList.add(String.format("--%s=%s", field.getName(), str));
                    }
                } else {
                    argvList.add(String.format("--%s=%s", field.getName(), value));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return argvList.toArray(new String[0]);
    }
}
