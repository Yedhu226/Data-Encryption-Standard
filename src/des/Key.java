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

import static des.Converter.HexToBinary;
import static des.Shifter.shift;
import static des.Transformer.perm_pc1;
import static des.Transformer.perm_pc2;

/**
 *
 * @author yedhu226
 */
public class Key {

    private String key;
    private String[][][] p_key = new String[2][4][7];
    private String[][][] r_key = new String[16][6][8];

    public Key(String k) {
        this.key = k;
        this.key = HexToBinary(this.key);
        this.p_key=perm_pc1(this.key);
        for(int i=0;i<16;i++){
            shift(p_key[0],i);
            shift(p_key[1],i);
            r_key[i]=perm_pc2(p_key);
        }
        reset();
    }
    
    String[][] get_key(int r){
        return r_key[r];
    }
    
    final void reset(){
        this.p_key=perm_pc1(this.key);
    }

}
