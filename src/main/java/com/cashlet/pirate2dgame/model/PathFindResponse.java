package com.cashlet.pirate2dgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathFindResponse {

    List<List<Integer>> path;
    Integer coins;

}
