import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";

        m = replace(m);

        int maxTime = 0;

        for (String info : musicinfos) {
            String[] infoArr = info.split(",");
            String sHour = infoArr[0].split(":")[0];
            String sMin = infoArr[0].split(":")[1];
            String eHour = infoArr[1].split(":")[0];
            String eMin = infoArr[1].split(":")[1];
            String title = infoArr[2];
            String mellody = infoArr[3];

            int totalTime = (Integer.parseInt(eHour) * 60
                    + Integer.parseInt(eMin)) 
                    - (Integer.parseInt(sHour) * 60
                    + Integer.parseInt(sMin));
                     
            mellody = replace(mellody);

            int mSize = mellody.length();

            String fullMellody = "";

            for (int i = 0; i < totalTime; i++) {
                fullMellody += mellody.charAt(i % mSize);
            }

            if (fullMellody.contains(m)) {
                if (maxTime < totalTime) {
                    maxTime = totalTime;
                    answer = title;
                }
            }

        }
        return answer;
    }

    private String replace(String s) {
        s = s.replace("C#", "c");
        s = s.replace("D#", "d");
        s = s.replace("E#", "e");
        s = s.replace("F#", "f");
        s = s.replace("G#", "g");
        s = s.replace("A#", "a");
        s = s.replace("B#", "b");

        return s;
    }
}
