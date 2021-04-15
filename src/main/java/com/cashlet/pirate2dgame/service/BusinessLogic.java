package com.cashlet.pirate2dgame.service;

import com.cashlet.pirate2dgame.model.Cell;
import com.cashlet.pirate2dgame.model.CellCoordinate;
import com.cashlet.pirate2dgame.model.PathFindResponse;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BusinessLogic {

    public PathFindResponse getMapping(Cell[][] arrayOfArrays, int x0, int y0, int xn, int yn){
        int x = x0, y = y0, score = 0;

        List<List<Integer>> array = new ArrayList<>();
        array.add(Arrays.asList(x, y));

        while (x != xn & y != yn){
            CellCoordinate fromRight = this.goRight(arrayOfArrays, x, y);
            CellCoordinate fromUp = this.goUp(arrayOfArrays, x, y);
            CellCoordinate next = null;

            int maxAmount = Math.max(fromRight.getAmount(), fromUp.getAmount());

            if (maxAmount == fromRight.getAmount())
                next = fromRight;
            if (maxAmount == fromUp.getAmount())
                next = fromUp;
            else
                return null;

            x = next.getCoordinate()[0];
            y = next.getCoordinate()[1];
            score = score + next.getAmount();

            array.add(Arrays.asList(x, y));
        }
        return new PathFindResponse(array, score);
    }

    public CellCoordinate goRight(Cell[][] arrayOfArrays, int x, int y){
        Cell cell = new Cell();
        try {
            cell = arrayOfArrays[x+1][y];
        }catch (ArrayIndexOutOfBoundsException exception){
            return null;
        }
        return new CellCoordinate(new int[]{x + 1, y},cell.getAmount());
    }

    public CellCoordinate goUp(Cell[][] arrayOfArrays, int x, int y){
        Cell cell = new Cell();
        try {
            cell = arrayOfArrays[x][y+1];
        }catch (ArrayIndexOutOfBoundsException exception){
            return null;
        }
        return new CellCoordinate(new int[]{x, y + 1}, cell.getAmount());
    }
}
