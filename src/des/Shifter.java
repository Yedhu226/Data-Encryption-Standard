package des;

import java.util.LinkedList;
import java.util.Queue;

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

/**
 *
 * @author yedhu226
 */
public class Shifter {
    static int[] schedule={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
    
    static String shift(String in,int r){
        String result=in.substring(schedule[r]);
        result=result+in.substring(0, r);
        return result;        
    }
    
    static void shift(String[][] in, int r){
        
        Queue<String> hold =new LinkedList<>();
        int i=schedule[r];
        for(int x=0;x<i;x++)
            hold.add(in[0][x]);
        int j=0;
        out:for (String[] in1 : in) {
            for (int q = 0; q < in1.length; q++) {
                in1[q] = in[j][i];
                i++;
                if (i >= in1.length || j>=in.length) {
                    i=0;
                    j++;
                    if (j==in.length) {
                        for (String _ : hold) {
                            in1[q] = hold.remove();
                            q++;
                        }
                        break out;
                    }
                }
            }
        }
    }
}
