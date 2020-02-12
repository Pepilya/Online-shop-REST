
package com.springboottest.app.model;

public class User {
    private int id;
    private String name;
    private String email;

    public User() {
    }

    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode(){
        int result = 1;
        final int prime = 31;
        result = prime * result + id;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object){
        User other = (User)object;
        if (this == object)
            return true;
        else if (object == null)
            return false;
        else if (this.getId() != other.getId())
            return false;
        else if (other.getName() == null || !(this.getName().equals(other.name)))
            return false;
        else if (other.getEmail() == null || !(this.getEmail().equals(other.email)))
            return false;
        else
            return true;
    }

    @Override
    public String toString(){
        return "User id: " + id + " User name: " + name + " User email: " + email;
    }
}
