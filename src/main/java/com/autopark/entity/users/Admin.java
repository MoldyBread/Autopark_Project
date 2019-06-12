package com.autopark.entity.users;


/**
 *
 * Class that represents user type of ADMIN
 *
 * @author Liash Danylo
 */
public class Admin extends User {

    private Admin(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     *
     * Builder class for Admin
     *
     */
    public static class Builder extends User.AbstractBuilder<Builder, Admin> {

        protected Builder self() {
            return this;
        }

        @Override
        public Admin build() {
            return new Admin(this);
        }
    }


}
