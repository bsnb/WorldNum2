package cn.edu.swufe.worldnum2;

public class NumItem {
    private int id;
    private String Name;
    private String Num;

    public NumItem() {
        super();
        this.Name = "";
        this.Num = "";
    }

    public NumItem(String Name, String Num) {
        this.Name = Name;
        this.Num = Num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String Num) {
        this.Num = Num;
    }
}

