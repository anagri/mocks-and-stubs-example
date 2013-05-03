package com.obc.testdoubles;

import org.joda.time.LocalDateTime;

import static org.joda.time.DateTimeConstants.FRIDAY;
import static org.joda.time.DateTimeConstants.MONDAY;

public class Library {

    private final IssueService issueService = new IssueService();
    private final SearchService searchService = new SearchService();

    public boolean isOpen() {
        LocalDateTime now = new LocalDateTime();
        if (now.getDayOfWeek() < MONDAY || now.getDayOfWeek() > FRIDAY)
            return false;

        if (now.getHourOfDay() <= 9 && now.getHourOfDay() >= 18)
            return false;
        return true;
    }

    public boolean issueBook(String isbn) {
        Book book = searchService.find(isbn);
        if (book != null) {
            issueService.issueBook(book);
            return true;
        }
        return false;
    }
}
