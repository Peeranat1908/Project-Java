package ku.cs.controllers;

import java.util.Stack;

public class NavigationHistoryService {
    private static NavigationHistoryService instance;
    private Stack<String> pageHistory = new Stack<>();

    private NavigationHistoryService() {}

    public static NavigationHistoryService getInstance() {
        if (instance == null) {
            instance = new NavigationHistoryService();
        }
        return instance;
    }

    public void pushPage(String page) {
        pageHistory.push(page);
    }

    public String popPage() {
        return pageHistory.isEmpty() ? null : pageHistory.pop();
    }

    public String peekPage() {
        return pageHistory.isEmpty() ? null : pageHistory.peek();
    }

}
