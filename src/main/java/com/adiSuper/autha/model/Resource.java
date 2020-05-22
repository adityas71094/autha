package com.adiSuper.autha.model;

public enum Resource
{
    USER(User.class), CLIENT(Client.class);


    private Class resourceClass;

    public Class getResourceClass()
    {
        return this.resourceClass;
    }

    private Resource(Class resourceClass)
    {
        this.resourceClass = resourceClass;
    }
}
