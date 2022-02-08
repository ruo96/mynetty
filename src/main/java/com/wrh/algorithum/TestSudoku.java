package com.wrh.algorithum;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wuruohong
 * @Classname TestSudoku
 * @Description 数独
 * @Date 2022/2/7 17:07
 */
public class TestSudoku {

    char[][] board1 = {{'5','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}};

    /** 方法1 */
    public boolean isValidSudoku(char[][] board) {
        for(int i=0; i<9; i++){
            Set setLine = new HashSet();
            Set setCol = new HashSet();
            Set setBox = new HashSet();
            for(int j = 0; j<9; j++){
                if(board[i][j]!='.' && !setLine.add(board[i][j])){
                    return false;
                }
                if(board[j][i]!='.' && !setCol.add(board[j][i])){
                    return false;
                }
                int a = (i/3)*3 + j/3;
                int b = (i%3)*3 + j%3;
                if(board[a][b] != '.' && !setBox.add(board[a][b])){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        int[] line = new int[9];
        int[] column = new int[9];
        int[] cell = new int[9];
        int shift = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                shift = 1 << (board[i][j] - '1');
                int k = (i / 3) * 3 + j / 3;
                //如果对应的位置只要有一个大于0，说明有冲突，直接返回false
                if ((column[i] & shift) > 0 || (line[j] & shift) > 0 || (cell[k] & shift) > 0)
                    return false;
                column[i] |= shift;
                line[j] |= shift;
                cell[k] |= shift;
            }
        }
        return true;
    }

    @Test
    public void Test48() {
        System.out.println("isValidSudoku(board1) = " + isValidSudoku(board1));
    }
}
