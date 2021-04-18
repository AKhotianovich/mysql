public class Abonent {
    private int id;
    private String name;
    private int phone;

    public Abonent(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;

    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Abonent [id=" + id + ", phone=" + phone +
                ", lastname=" + name + "]";
    }


}
