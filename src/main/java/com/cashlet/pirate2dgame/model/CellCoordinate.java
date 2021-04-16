package com.cashlet.pirate2dgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellCoordinate {
    Cell cell;
    int[] coordinate;
    Integer amount;
}
