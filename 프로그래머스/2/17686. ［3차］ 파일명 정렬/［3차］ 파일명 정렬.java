import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String[] solution(String[] files) {
        List<FileName> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            boolean flag = false;
            int startIdx = -1;
            int endIdx = -1;
            String word = files[i];
            for (int j = 0; j < word.length(); j++) {
                if (!flag && startIdx == - 1 && Character.isDigit(word.charAt(j))) {
                    startIdx = j;
                    flag = true;
                } else if (flag && endIdx == -1 && !Character.isDigit(word.charAt(j))) {
                    endIdx = j;
                    flag = false;
                    break;
                }
            }

            if (startIdx != -1 && endIdx == -1) {
                endIdx = word.length();
            }

            list.add(new FileName(word.substring(0, startIdx), word.substring(startIdx, endIdx), Integer.parseInt(word.substring(startIdx, endIdx)), word.substring(endIdx)));
        }

        Collections.sort(list);
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).getOriginal();
        }
        return answer;
    }

    class FileName implements Comparable<FileName>{
        private String head;
        private String numberStr;
        private int number;
        private String tail;

        FileName(String head, String numberStr, int number, String tail) {
            this.head = head;
            this.numberStr = numberStr;
            this.number = number;
            this.tail = tail;
        }

        public String getOriginal() {
            return this.head + this.numberStr + this.tail;
        }

        @Override
        public int compareTo(FileName o) {
            int headRes = this.head.equalsIgnoreCase(o.head) ? 0 : this.head.toLowerCase().compareTo(o.head.toLowerCase());

            if (headRes != 0) {
                return headRes;
            }

            if (this.number != o.number) {
                return this.number - o.number;
            }

            return 0;
        }
    }
}