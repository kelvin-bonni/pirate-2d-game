package com.cashlet.pirate2dgame.service;

import com.cashlet.pirate2dgame.model.Cell;
import com.cashlet.pirate2dgame.model.CellCoordinate;
import com.cashlet.pirate2dgame.model.PathFindResponse;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class BusinessLogic {

    public PathFindResponse getMapping(Cell[][] arrayOfArrays, int x0, int y0, int xn, int yn){
        int x = x0, y = y0, score = 0;

        List<List<Integer>> array = new ArrayList<>();
        array.add(Arrays.asList(x, y));

        while (x != xn & y != yn){
            //make a choice to go right or up
            //use the greedy algo (eg. Hill climbing)
            //pick closest cell with greater number of treasure
            CellCoordinate next = null;
            CellCoordinate fromRight;
            CellCoordinate fromUp;
            try {
                fromRight = this.goRight(arrayOfArrays, x, y);
                fromUp = this.goUp(arrayOfArrays, x, y);
            }catch (ArrayIndexOutOfBoundsException e){
                return null;
            }

            int maxAmount = Math.max(fromRight.getAmount(), fromUp.getAmount());

            if (maxAmount == fromRight.getAmount())
                next = fromRight;
            if (maxAmount == fromUp.getAmount())
                next = fromUp;

            if (Objects.isNull(next))
                return null;

            x = next.getCoordinate()[0];
            y = next.getCoordinate()[1];
            score = score + next.getAmount();

            array.add(Arrays.asList(x, y));
        }
        return new PathFindResponse(array, score);
    }

    public CellCoordinate goRight(Cell[][] arrayOfArrays, int x, int y) throws ArrayIndexOutOfBoundsException{
        //Increase x by 1 and maintain y
        //If array bound is exceeded throw an exception
        Cell cell = arrayOfArrays[x+1][y];
        Integer amount = null;
        if (Objects.equals(cell.getType(), "coins"))
            amount = cell.getAmount();
        return new CellCoordinate(new int[]{x + 1, y}, amount);
    }

    public CellCoordinate goUp(Cell[][] arrayOfArrays, int x, int y) throws ArrayIndexOutOfBoundsException{
        //Increase y by 1 and maintain x
        //If array bound is exceeded throw an exception
        Cell cell = arrayOfArrays[x][y+1];
        Integer amount = null;
        if (Objects.equals(cell.getType(), "coins"))
            amount = cell.getAmount();
        return new CellCoordinate(new int[]{x, y + 1}, amount);
    }
}
