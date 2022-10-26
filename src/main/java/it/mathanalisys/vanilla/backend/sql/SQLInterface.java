package it.mathanalisys.vanilla.backend.sql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SQLInterface {

    @Getter
    private String driverUrl;
    private String user;
    private String password;
    private int port;

    public SQLInterface(String driverUrl, String user, String password, int port){
        try {Class.forName("org.mariadb.jdbc.Driver");}
        catch (ClassNotFoundException e){e.printStackTrace();}
        this.driverUrl = driverUrl;
        this.user = user;
        this.password = password;
        this.port = port;
    }
}