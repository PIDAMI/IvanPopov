package com.epam.tc.hw5.entities;

import java.util.Arrays;

public class UserTableRow {

    private Long number;
    private Role selectedValue;
    private String description;
    private String userName;
    private Boolean isCheckboxChecked;

    public UserTableRow(Long number, Role selectedValue,
                        String description, String userName,
                        Boolean isCheckboxChecked) {
        this.number = number;
        this.selectedValue = selectedValue;
        this.description = description;
        this.userName = userName;
        this.isCheckboxChecked = isCheckboxChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserTableRow)) {
            return false;
        }
        UserTableRow other = (UserTableRow) o;
        var fields = this.getClass().getDeclaredFields();
        return Arrays.stream(fields).allMatch(field -> {
            field.setAccessible(true);
            boolean res = false;
            try {
                Object thisVal = field.get(this);
                Object otherVal = field.get(other);
                res = thisVal == null || otherVal == null || thisVal.equals(otherVal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return res;
        });
    }

    public enum Role {
        ADMIN("ADMIN"),
        USER("USER"),
        MANAGER("MANAGER");

        private final String val;
        Role(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }

    }
}
