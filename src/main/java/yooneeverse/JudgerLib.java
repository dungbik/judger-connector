package yooneeverse;

import com.sun.jna.Library;
import com.sun.jna.Native;

import static yooneeverse.Constants.JUDGER_PATH;

public interface JudgerLib extends Library {
    JudgerLib INSTANCE = Native.load(JUDGER_PATH, JudgerLib.class);
    Result.ByValue judge(int argc, String[] argv);
}
