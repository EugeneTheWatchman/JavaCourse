package ru.croc.task13;

import java.util.List;
import java.util.Set;

public class CommentsFilter implements BlackListFilter {

    private boolean isStringContainsWords(String comment, Set<String> blackList) {
        for (String blackWord : blackList) {
            if (comment.toLowerCase().contains(blackWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (int i = 0; i < comments.size(); i++) {
            String comment = comments.get(i);

            boolean help = isStringContainsWords(comment, blackList);

            if (!isStringContainsWords(comment, blackList)) {
                continue;
            }

            comments.remove(i);
            i--;
        }
    }
}
