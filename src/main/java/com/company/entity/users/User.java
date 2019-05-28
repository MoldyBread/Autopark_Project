package com.company.entity.users;

public abstract class User {
    private final Long id;
    private final String login;
    private final String password;
    private final UserType userType;


    protected User(AbstractBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.userType = builder.userType;
    }

    public static abstract class AbstractBuilder<T extends AbstractBuilder,E extends User> {
        private  Long id;
        private  String login;
        private  String password;
        private  UserType userType;

        protected abstract T self();

        public T withId(Long id) {
            this.id=id;
            return self();
        }

        public T withLogin(String login) {
            this.login=login;
            return self();
        }

        public T withPassword(String password) {
            this.password=password;
            return self();
        }

        public T withUserType(UserType userType) {
            this.userType=userType;
            return self();
        }

        public abstract E build();


    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
