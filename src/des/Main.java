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

import static des.Converter.BinaryToHex;
import static des.Converter.HexToBinary;
import static des.Substitution.substitute;
import static des.Transformer.XOR;
import static des.Transformer.flatten;
import static des.Transformer.perm_ep;
import static des.Transformer.perm_ip;
import static des.Transformer.perm_ip_inv;
import static des.Transformer.perm_p;
import java.util.Scanner;

/**
 *
 * @author yedhu226
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter 64-bit Plaintext in HexaDecimal format: ");
        String P=sc.nextLine();
        System.out.println("Enter Key in HexaDecimal format: ");
        String K=sc.nextLine();
        String C=encrypt(P,K);
        System.out.println("Cipher is "+C);
        P=decrypt(C,K);
        System.out.println("Plaintext is "+P);
        
    }
    
    static String encrypt(String P, String K){
        Key k=new Key(K);
        P=HexToBinary(P);
        String[][] mat_P=perm_ip(P);
        String L="";
        String R="";
        for(String[] i:mat_P){
            for(String j:i){
                if(L.length()<32)
                    L+=j;
                else
                    R+=j;
            }
        }
        for(int i=0;i<16;i++){
            String[][] Re=perm_ep(R);
            Re=XOR(Re,k.get_key(i));
            String t=substitute(Re);
            t=flatten(perm_p(t));
            t=XOR(L,t);
            L=R;
            R=t;
        }
        String t=R;
        R=L;
        L=t;
        L=L+R;
        return BinaryToHex(flatten(perm_ip_inv(L)));
    }
        static String decrypt(String P, String K){
        Key k=new Key(K);
        P=HexToBinary(P);
        String[][] mat_P=perm_ip(P);
        String L="";
        String R="";
        for(String[] i:mat_P){
            for(String j:i){
                if(L.length()<32)
                    L+=j;
                else
                    R+=j;
            }
        }
        for(int i=0;i<16;i++){
            String[][] Re=perm_ep(R);
            Re=XOR(Re,k.get_key(15-i));
            String t=substitute(Re);
            t=flatten(perm_p(t));
            t=XOR(L,t);
            L=R;
            R=t;
        }
        String t=R;
        R=L;
        L=t;
        L=L+R;
        return BinaryToHex(flatten(perm_ip_inv(L)));
    }

}
