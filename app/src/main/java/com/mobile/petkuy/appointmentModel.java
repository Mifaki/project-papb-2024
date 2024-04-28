package com.mobile.petkuy;

public class appointmentModel {

    private String NamaDok;

    public String getNamaDok() {
        return NamaDok;
    }

    private String Spesialis;

    public String getSpesialis() {
        return Spesialis;
    }

    private String NamaRS;


    public String getNamaRS() {
        return NamaRS;
    }

    private String JalanRS;


    public String getJalanRS() {
        return JalanRS;
    }

    private String Jadwal;

    public String getJadwal() {
        return Jadwal;
    }

    private int Image;

    public int getImage() {
        return Image;
    }

    public appointmentModel(int Image, String NamaDok, String Spesialis, String NamaRS, String JalanRS, String Jadwal){
        this.Image = Image;
        this.NamaDok = NamaDok;
        this.Spesialis = Spesialis;
        this.NamaRS = NamaRS;
        this.JalanRS = JalanRS;
        this.Jadwal = Jadwal;
    }

}
