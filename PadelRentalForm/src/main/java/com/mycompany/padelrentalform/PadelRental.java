/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.padelrentalform;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author HAK_PHENG
 */
public class PadelRental {
    private String nama;
    private int noHp;
    private Date tanggalSewa;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private String lapangan;

    public PadelRental(String nama, int noHp, Date tanggalSewa, LocalTime jamMulai, LocalTime jamSelesai, String lapangan) {
        this.nama = nama;
        this.noHp = noHp;
        this.tanggalSewa = tanggalSewa;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.lapangan = lapangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNoHp() {
        return noHp;
    }

    public void setNoHp(int noHp) {
        this.noHp = noHp;
    }

    public Date getTanggalSewa() {
        return tanggalSewa;
    }

    public void setTanggalSewa(Date tanggalSewa) {
        this.tanggalSewa = tanggalSewa;
    }

    public LocalTime getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(LocalTime jamMulai) {
        this.jamMulai = jamMulai;
    }

    public LocalTime getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(LocalTime jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getLapangan() {
        return lapangan;
    }
    
    public void setlapangan(String lapangan) {
        this.lapangan = lapangan;
    }
}
