package com.autopark.entity.users;

import java.util.Objects;

/**
 *
 * Class that represents user type of DRIVER
 *
 * @author Liash Danylo
 */
public class Driver extends User {
    private final String name;
    private final String surname;
    private boolean accepted;

    private Driver(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.surname = builder.surname;
        this.accepted = builder.accepted;
    }

    /**
     * Name getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Surname getter
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Workplace acceptation getter
     *
     * @return acceptation of worplace
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * Builder of driver
     * @return builder for driver
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     *
     * Builder class for Driver
     *
     */
    public static class Builder extends User.AbstractBuilder<Builder, Driver> {
        private String name;
        private String surname;
        private boolean accepted;

        protected Builder self() {
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return self();
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return self();
        }

        public Builder withAccepted(Boolean accepted) {
            this.accepted = accepted;
            return self();
        }

        public Driver build() {
            return new Driver(this);
        }
    }


    @Override
    public String toString() {
        return super.toString() +
                "Driver{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accepted=" + accepted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return accepted == driver.accepted &&
                Objects.equals(name, driver.name) &&
                Objects.equals(surname, driver.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, accepted);
    }
}
