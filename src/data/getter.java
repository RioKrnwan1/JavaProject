package data;

public class getter {
   private int id;
    private String username;
    private String password;
    private String namaLengkap;
    private String NIM;
    private String noHp;
    private String email;
    private String jenisKelamin;

    public getter(int id, String username, String password, String namaLengkap,
            String NIM, String noHp, String email, String jenisKelamin ) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.NIM = NIM;
        this.noHp = noHp;
        this.email = email;
        this.jenisKelamin = jenisKelamin;

    }

    public int getId() { 
        return id; 
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getNamaLengkap() {
        return namaLengkap;
    }
    public String getNIM() {
        return NIM;
    }
    public String getNoHp() {
        return noHp;
    }
    public String getEmail() {
        return email;
    }
    public String getJenisKelamin() {
        return jenisKelamin;
    }
}