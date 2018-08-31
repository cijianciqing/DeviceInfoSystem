package jsjzx.wlyw.networkmaintenance.entities;

public class MyModel {

    private Integer id;
    private String model;
    private Typebrand typebrand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Typebrand getTypebrand() {
        return typebrand;
    }

    public void setTypebrand(Typebrand typebrand) {
        this.typebrand = typebrand;
    }

    @Override
    public String toString() {
        return "MyModel{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", typebrand=" + typebrand +
                '}';
    }
}
