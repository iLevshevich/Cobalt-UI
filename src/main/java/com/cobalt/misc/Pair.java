package com.cobalt.misc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Pair<T, U> {
    private T key;
    private U value;
}
