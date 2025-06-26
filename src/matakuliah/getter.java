package matakuliah;

public class getter {
   private int id;
    private String kodeMatkul;
    private String namaMatkul;
    private String sks;
    

    public getter(int id, String kodeMatkul, String namaMatkul, String sks ) {

        this.id = id;
        this.kodeMatkul = kodeMatkul;
        this.namaMatkul = namaMatkul;
        this.sks = sks;
    }

    public int getId() { 
        return id; 
    }
    public String getKodeMatkul() {
        return kodeMatkul;
    }
    public String getNamaMatkul() {
        return namaMatkul;
    }
    public String getSks() {
        return sks;
    }
}