package main.java.classes;

public class Client {

    private Integer idCliente;
    private String email;
    private String name;

    //constructores

    public Client(Integer idCliente, String email, String name) {
        this.idCliente = idCliente;
        this.email = email;
        this.name = name;
    }


    //getters and setters

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
