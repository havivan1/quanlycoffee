package com.example.miniproject.util;

public class GiauThongTin {
    public static String AnEmail(String email) {
        int index = email.indexOf("@");
        if (index <= 1) {
            return email;
        }
        return "***" + email.substring(index);
    }

    public static String AnSDT(String sdt) {
        if (sdt.length() <= 2) {
            return sdt;
        }
        return sdt.charAt(0) + "*******" + sdt.charAt(sdt.length() - 1);
    }

    public static String AnMatKhau(String matkhau) {
        return "********" ;
    }
}
