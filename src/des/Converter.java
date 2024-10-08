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

import java.math.BigInteger;

/**
 *
 * @author yedhu226
 */
public class Converter {

    static String HexToBinary(String in) {
        String h = new BigInteger(in, 16).toString(2);
        while (h.length() < 64) {
            h = "0" + h;
        }
        return h;
    }

    static String BinaryToHex(String in) {
        return new BigInteger(in, 2).toString(16);
    }

    static String DecimalToBinary(int n) {
        String out = Integer.toBinaryString(n);
        while (out.length() < 4) {
            out = "0" + out;
        }
        return out;
    }

    static int BinaryToDecimal(String n) {
        return Integer.parseInt(n, 2);
    }
}
