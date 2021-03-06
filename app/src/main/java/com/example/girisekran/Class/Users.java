package com.example.girisekran.Class;

public class Users {
    String userId;
    String userIsim;
    String userSoyisim;
    int userTelefonNumarası;


    String userEmail;
    String userDogumTarihi;
    String userMeslek;
    String userSehir;
    String userYas;

    public Users(String userMeslek, String userSehir, String userYas, String userBoy, String userKilo) {
        this.userMeslek = userMeslek;
        this.userSehir = userSehir;
        this.userYas = userYas;
        this.userBoy = userBoy;
        this.userKilo = userKilo;
    }

    String userBoy;
    String userKilo;
    String userOlcumBel;

    public Users() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIsim() {
        return userIsim;
    }

    public void setUserIsim(String userIsim) {
        this.userIsim = userIsim;
    }

    public String getUserSoyisim() {
        return userSoyisim;
    }

    public void setUserSoyisim(String userSoyisim) {
        this.userSoyisim = userSoyisim;
    }

    public int getUserTelefonNumarası() {
        return userTelefonNumarası;
    }

    public void setUserTelefonNumarası(int userTelefonNumarası) {
        this.userTelefonNumarası = userTelefonNumarası;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDogumTarihi() {
        return userDogumTarihi;
    }

    public void setUserDogumTarihi(String userDogumTarihi) {
        this.userDogumTarihi = userDogumTarihi;
    }

    public String getUserMeslek() {
        return userMeslek;
    }

    public void setUserMeslek(String userMeslek) {
        this.userMeslek = userMeslek;
    }

    public String getUserSehir() {
        return userSehir;
    }

    public void setUserSehir(String userSehir) {
        this.userSehir = userSehir;
    }

    public String getUserBoy() {
        return userBoy;
    }

    public void setUserBoy(String userBoy) {
        this.userBoy = userBoy;
    }

    public String getUserKilo() {
        return userKilo;
    }

    public void setUserKilo(String userKilo) {
        this.userKilo = userKilo;
    }

    public String getUserOlcumBel() {
        return userOlcumBel;
    }

    public void setUserOlcumBel(String userOlcumBel) {
        this.userOlcumBel = userOlcumBel;
    }

    public String getUserOlcumKalca() {
        return userOlcumKalca;
    }

    public void setUserOlcumKalca(String userOlcumKalca) {
        this.userOlcumKalca = userOlcumKalca;
    }

    public String getUserOlcumGogus() {
        return userOlcumGogus;
    }

    public void setUserOlcumGogus(String userOlcumGogus) {
        this.userOlcumGogus = userOlcumGogus;
    }

    public String getUserOlcumBacak() {
        return userOlcumBacak;
    }

    public void setUserOlcumBacak(String userOlcumBacak) {
        this.userOlcumBacak = userOlcumBacak;
    }

    public String getUserHastalıkAdi() {
        return userHastalıkAdi;
    }

    public void setUserHastalıkAdi(String userHastalıkAdi) {
        this.userHastalıkAdi = userHastalıkAdi;
    }

    public String getUserTahlildeOnemliUnsurlar() {
        return userTahlildeOnemliUnsurlar;
    }

    public void setUserTahlildeOnemliUnsurlar(String userTahlildeOnemliUnsurlar) {
        this.userTahlildeOnemliUnsurlar = userTahlildeOnemliUnsurlar;
    }

    public String getUserDanisanHikayesi() {
        return userDanisanHikayesi;
    }

    public void setUserDanisanHikayesi(String userDanisanHikayesi) {
        this.userDanisanHikayesi = userDanisanHikayesi;
    }

    public Users(String userId, String userIsim, String userSoyisim, int userTelefonNumarası, String userEmail,
                 String userDogumTarihi, String userMeslek, String userSehir, String userBoy,
                 String userKilo, String userOlcumBel, String userOlcumKalca, String userOlcumGogus,
                 String userOlcumBacak, String userHastalıkAdi, String userTahlildeOnemliUnsurlar,
                 String userDanisanHikayesi) {
        this.userId = userId;
        this.userIsim = userIsim;
        this.userSoyisim = userSoyisim;
        this.userTelefonNumarası = userTelefonNumarası;
        this.userEmail = userEmail;
        this.userDogumTarihi = userDogumTarihi;
        this.userMeslek = userMeslek;
        this.userSehir = userSehir;
        this.userBoy = userBoy;
        this.userKilo = userKilo;
        this.userOlcumBel = userOlcumBel;
        this.userOlcumKalca = userOlcumKalca;
        this.userOlcumGogus = userOlcumGogus;
        this.userOlcumBacak = userOlcumBacak;
        this.userHastalıkAdi = userHastalıkAdi;
        this.userTahlildeOnemliUnsurlar = userTahlildeOnemliUnsurlar;
        this.userDanisanHikayesi = userDanisanHikayesi;
    }

    public Users(String userId, String userIsim, String userSoyisim, int userTelefonNumarası, String userEmail, String userDogumTarihi) {
        this.userId = userId;
        this.userIsim = userIsim;
        this.userSoyisim = userSoyisim;
        this.userTelefonNumarası = userTelefonNumarası;
        this.userEmail = userEmail;
        this.userDogumTarihi = userDogumTarihi;
    }

    public Users(String userMeslek, String userSehir, String userBoy, String userKilo, String userOlcumBel, String userOlcumKalca, String userOlcumGogus, String userOlcumBacak) {
        this.userMeslek = userMeslek;
        this.userSehir = userSehir;
        this.userBoy = userBoy;
        this.userKilo = userKilo;
        this.userOlcumBel = userOlcumBel;
        this.userOlcumKalca = userOlcumKalca;
        this.userOlcumGogus = userOlcumGogus;
        this.userOlcumBacak = userOlcumBacak;
    }

    public Users(String userHastalıkAdi, String userTahlildeOnemliUnsurlar, String userDanisanHikayesi) {
        this.userHastalıkAdi = userHastalıkAdi;
        this.userTahlildeOnemliUnsurlar = userTahlildeOnemliUnsurlar;
        this.userDanisanHikayesi = userDanisanHikayesi;
    }

    String userOlcumKalca;
    String userOlcumGogus;
    String userOlcumBacak;
    String userHastalıkAdi;
    String userTahlildeOnemliUnsurlar;
    String userDanisanHikayesi;


}
