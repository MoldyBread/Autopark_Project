package com.company.entity.users;

public class Driver extends User{
    private final String name;
    private final String surname;
    private boolean accepted;

    public Driver(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.surname = builder.surname;
        this.accepted = builder.accepted;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends User.AbstractBuilder {
        private String name;
        private String surname;
        private boolean accepted;

        protected Builder self() {
            return this;
        }

        public Builder withName(String name) {
            this.name=name;
            return self();
        }

        public Builder withSurname(String surname) {
            this.surname=surname;
            return self();
        }

        public Builder withAccepted(Boolean accepted) {
            this.accepted=accepted;
            return self();
        }

        public Driver build() {
            return new Driver(this);
        }
    }



    @Override
    public String toString() {
        return  super.toString()+
                "Driver{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accepted=" + accepted +
                '}';
    }


}
