package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<UserData>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<UserData>();
    }

    public Users(Collection<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public Users withAdded(UserData user){
        //выдает новый объект, с добавленным пользователем
        Users users = new Users(this); //конструктор будет строить копию
        users.add(user);
        return users;
    }

    public Users without(UserData user){
        Users users = new Users(this); //конструктор будет строить копию
        users.remove(user);
        return users;
    }

    public UserData getUserInfo(UserData contact) {
        UserData user = null;
        for(UserData contactData : delegate){
            if(contactData.getId() == contact.getId()){
                user = contactData;
                break;
            }
        }
        return user;
    }
}
