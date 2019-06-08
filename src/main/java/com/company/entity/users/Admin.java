package com.company.entity.users;


public class Admin extends User {

    private Admin(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }



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
