/*
 * Copyright (C) 2024 yedhu226
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package des;

/**
 *
 * @author yedhu226
 */
public class Transformer {

    static int[][] p_ip = {
        {58, 50, 42, 34, 26, 18, 10, 2},
        {60, 52, 44, 36, 28, 20, 12, 4},
        {62, 54, 46, 38, 30, 22, 14, 6},
        {64, 56, 48, 40, 32, 24, 16, 8},
        {57, 49, 41, 33, 25, 17, 9, 1},
        {59, 51, 43, 35, 27, 19, 11, 3},
        {61, 53, 45, 37, 29, 21, 13, 5},
        {63, 55, 47, 39, 31, 23, 15, 7}};
    static int[][] p_ip_inv = {
        {40, 8, 48, 16, 56, 24, 64, 32},
        {39, 7, 47, 15, 55, 23, 63, 31},
        {38, 6, 46, 14, 54, 22, 62, 30},
        {37, 5, 45, 13, 53, 21, 61, 29},
        {36, 4, 44, 12, 52, 20, 60, 28},
        {35, 3, 43, 11, 51, 19, 59, 27},
        {34, 2, 42, 10, 50, 18, 58, 26},
        {33, 1, 41, 9, 49, 17, 57, 25}};
    static int[][] p_ep = {
        {32, 1, 2, 3, 4, 5},
        {4, 5, 6, 7, 8, 9},
        {8, 9, 10, 11, 12, 13},
        {12, 13, 14, 15, 16, 17},
        {16, 17, 18, 19, 20, 21},
        {20, 21, 22, 23, 24, 25},
        {24, 25, 26, 27, 28, 29},
        {28, 29, 30, 31, 32, 1}};
    static int[][] p_p = {
        {16, 7, 20, 21, 29, 12, 28, 17},
        {1, 15, 23, 26, 5, 18, 31, 10},
        {2, 8, 24, 14, 32, 27, 3, 9},
        {19, 13, 30, 6, 22, 11, 4, 25}};
    static int[][][] p_pc1 = {
        {
            {57, 49, 41, 33, 25, 17, 9},
            {1, 58, 50, 42, 34, 26, 18},
            {10, 2, 59, 51, 43, 35, 27},
            {19, 11, 3, 60, 52, 44, 36}
        },
        {
            {63, 55, 47, 39, 31, 23, 15},
            {7, 62, 54, 46, 38, 30, 22},
            {14, 6, 61, 53, 45, 37, 29},
            {21, 13, 5, 28, 20, 12, 4}
        }
    };

    static int[][][] p_pc2 = {
        {   // C (left half)
            {14, 17, 11, 24, 1, 5},
            {3, 28, 15, 6, 21, 10},
            {23, 19, 12, 4, 26, 8},
            {16, 7, 27, 20, 13, 2}
        },
        {   // D (right half)
            {41, 52, 31, 37, 47, 55},
            {30, 40, 51, 45, 33, 48},
            {44, 49, 39, 56, 34, 53},
            {46, 42, 50, 36, 29, 32}
        }
    };
    
    static String[][] perm_ip(String in) {
        String[][] result = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result[i][j] = Character.toString(in.charAt(p_ip[i][j] - 1));
            }
        }
        return result;
    }

    static String[][] perm_ip_inv(String in) {
        String[][] result = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result[i][j] = Character.toString(in.charAt(p_ip_inv[i][j] - 1));
            }
        }
        return result;
    }

    static String[][] perm_ep(String in) {
        String[][] result = new String[8][6];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                result[i][j] = Character.toString(in.charAt(p_ep[i][j] - 1));
            }
        }
        return result;
    }

    static String[][] perm_p(String in) {
        String[][] result = new String[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                result[i][j] = Character.toString(in.charAt(p_p[i][j] - 1));
            }
        }
        return result;
    }

    static String[][][] perm_pc1(String in) {
        String[][][] result = new String[2][4][7];
        int p = 0;
        while (p <= 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    result[p][i][j] = Character.toString(in.charAt(p_pc1[p][i][j]-1));
                }
            }
            p++;
        }
        return result;
    }

    static String[][] perm_pc2(String[][][] k) {
        String[][] result = new String[8][6];
        int x=0;
        String c=flatten(k[0]);
        String d=flatten(k[1]);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                if(i<4)
                    result[i][j] = Character.toString(c.charAt(p_pc2[0][i][j] - 1));
                else
                    result[i][j] = Character.toString(d.charAt(p_pc2[1][i-4][j] - 29));
            }
        }
        return result;
    }

    static String[][] XOR(String[][] A, String[][] B) {
        String[][] result = new String[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j].equals(B[i][j])) {
                    result[i][j] = "0";
                } else {
                    result[i][j] = "1";
                }
            }
        }
        return result;
    }

    static String XOR(String A, String B) {
        String out = "";
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == B.charAt(i)) {
                out = out + "0";
            } else {
                out = out + "1";
            }
        }
        return out;
    }

    static String flatten(String[][] in) {
        String result = "";
        for (String[] i : in) {
            for (String j : i) {
                result += j;
            }
        }
        return result;
    }

}
