package TemporaryStorageInJvm;

import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

public class CurrentUser
{
    static Builder object;
    public static Builder getBuilder()
    {  
        return object;  
    }  
    
    public void setBuilder(Builder create) 
    {  
        object =create;  
    }      
}