package com.epam.tc.hw5.entities;

import java.util.List;

public class UserTable {

    private final List<UserTableRow> rows;

    public UserTable(List<UserTableRow> rows) {
        this.rows = rows;
    }

    public List<UserTableRow> getRows() {
        return rows;
    }
}
