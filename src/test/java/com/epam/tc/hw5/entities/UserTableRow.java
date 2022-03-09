package com.epam.tc.hw5.entities;

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

        return (other.number == null || number == null || other.number.equals(number))
            && (other.selectedValue == null || selectedValue == null || other.selectedValue.equals(selectedValue))
            && (other.description == null || description == null || other.description.equals(description))
            && (other.userName == null || userName == null || other.userName.equals(userName))
            && (other.isCheckboxChecked == null || isCheckboxChecked == null
                || other.isCheckboxChecked.equals(isCheckboxChecked));
    }

    public boolean isCheckboxChecked() {
        return isCheckboxChecked;
    }

    public long getNumber() {
        return number;
    }

    public Role getSelectedValue() {
        return selectedValue;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
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
