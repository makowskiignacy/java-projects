public class Main {
    public static double minimum(double x, double y, double z) {
        if (x < y) {
            if (x < z) {
                return x;
            } else {
                return z;
            }
        } else {
            if (y < z) {
                return y;
            } else {
                return z;
            }
        }
    }
    public static double jaka_kara(double[][] kary, int x, int y, int x_max, int y_max, char[] a, char[] b, double kara_przerwa, double kara_litera) {
        if (kary[x][y] != -1) {
            return kary[x][y];
        } else {
            double ox = jaka_kara(kary, x + 1, y, x_max, y_max, a, b, kara_przerwa, kara_litera) + kara_przerwa;
            double oy = jaka_kara(kary, x, y + 1, x_max, y_max, a, b, kara_przerwa, kara_litera) + kara_przerwa;
            double oxy;
            if (a[x] == b[y]) {
                oxy = jaka_kara(kary, x + 1, y + 1, x_max, y_max, a, b, kara_przerwa, kara_litera);
            } else {
                oxy = jaka_kara(kary, x + 1, y + 1, x_max, y_max, a, b, kara_przerwa, kara_litera) + kara_litera;
            }
            kary[x][y] = minimum(ox, oy, oxy);
            return kary[x][y];
        }
    }
    public static double levenshtein(String a, String b, double kara_przerwa, double kara_litera) {
        //kary są dodatnie
        int m = a.length();
        int n = b.length();
        double[][] kary = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                kary[i][j] = -1;
            }
        }
        for (int i = 0; i < m; i++) {
            kary[i][n - 1] = (m - 1 - i) * kara_przerwa;
        }
        for (int i = 0; i < n; i++) {
            kary[m - 1][i] = (n - 1 -i) * kara_przerwa;
        }
        return jaka_kara(kary, 0, 0, m, n, a.toCharArray(), b.toCharArray(), kara_przerwa, kara_litera);
    }
    public static void main(String[] args) {
        String a = "AGATCTGTTCTCTAAACGAACTTTAAAATCTGTGTGGCTGTCACTCGGCTGCATGCTTAGTGCACTCACGCAGTATAATTAATAACTAATTACTGTCGTTGAGTTTGCCTGTTTTACAGGTTCGCGACGTGCTCGTACGTGGCTTTGGAGA";
        String b = "TTTCAACGAGAAAACACACGTCCAACTCAGTCTCGGCTGCATGCTTAGCTCGGCTGCATGCTTAGTTGCCTGTTTTACAGGTTCGCGACGTGCTCGTACGTGGCTTTGGAGACTCCGTGGAGGAGGTCTTATCAGAGCTCGGCTGCATGCTTAGGCACGT";
        String c ="AGATCTGTTCTCTAAACGAACTTTAAAATCTGTGTGGCTGTCACTCGGCTGCATGCTTAGTGCACTCACGCAGTATAATTAATAACTAATTACTGTCGTTGAGTTTGCCTGTTTTACAGGTTCGCGACGTGCTCGTACGTGGCTTTGGAGA";

        String covid19_first = "AGATCTGTTCTCTAAACGAACTTTAAAATCTGTGTGGCTGTCACTCGGCTGCATGCTTAGTGCACTCACGCAGTATAATTAATAACTAATTACTGTCGTTGACAGGACACGAGTAACTCGTCTATCTTCTGCAGGCTGCTTACGGTTTCGTCCGTGTTGCAGCCGATCATCAGCACATCTAGGTTTCGTCCGGGTGTGACCGAAAGGTAAGATGGAGAGCCTTGTCCCTGGTTTCAACGAGAAAACACACGTCCAACTCAGTTTGCCTGTTTTACAGGTTCGCGACGTGCTCGTACGTGGCTTTGGAGACTCCGTGGAGGAGGTCTTATCAGAGGCACGTCAACATCTTAAAGATGGCACTTGTGGCTTAGTAGAAGTTGAAAAAGGCGTTTTGCCTCAACTTGAACAGCCCTATGTGTTCATCAAACGTTCGGATGCTCGAACTGCACCTCATGGTCATGTTATGGTTGAGCTGGTAGCAGAACTCGAAGGCATTCA";
        String covid19_second = "GCCATAGTTACGGCGCCGATCTAAAGTCATTTGACTTAGGCGACGAGCTTGGCACTGATCCTTATGAAGATTTTCAAGAAAACTGGAACACTAAACATAGCAGTGGTGTTACCCGTGAACTCATGCGTGAGCTTAACGGAGGGGCATACACTCGCTATGTCGATAACAACTTCTGTGGCCCTGATGGCTACCCTCTTGAGTGCATTAAAGACCTTCTAGCACGTGCTGGTAAAGCTTCATGCACTTTGTCCGAACAACTGGACTTTATTGACACTAAGAGGGGTGTATACTGCTGCCGTGAACATGAGCATGAAATTGCTTGGTACACGGAACGTTCTGAAAAGAGCTATGAATTGCAGACACCTTTTGAAATTAAATTGGCAAAGAAATTTGACACCTTCAATGGGGAATGTCCAAATTTTGTATTTCCCTTAAATTCTATAATCAAGACTATTCAACCAAGGGTTGAAAAGAAAAAGCTTGATGGCTTTATGGGTA";
        double odleglosc1 = levenshtein(covid19_first, covid19_second, 0.01, 0.03);
        double odleglosc2 = levenshtein(covid19_first, covid19_first, 0.01, 0.03);
        System.out.print(odleglosc1 + "\n");
        System.out.print(odleglosc2 + "\n");
    }
}
